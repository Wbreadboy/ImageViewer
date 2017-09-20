package com.breadboy.android.imageviewer.detailedimage.presenter

import android.util.Log
import com.breadboy.android.imageviewer.data.image.DetailedImage
import com.breadboy.android.imageviewer.detailedimage.DetailedImageContract
import com.breadboy.android.imageviewer.detailedimage.view.DetailedImageActivity
import com.breadboy.android.imageviewer.imagelist.view.ImageListActivity
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import javax.inject.Inject

/**
 * Created by N4039 on 2017-09-18.
 */

class DetailedImagePresenter

@Inject
constructor(val detailedImageActivity: DetailedImageActivity): DetailedImageContract.Presenter {

    val detailedImageHashMap: HashMap<String, DetailedImage>
        get() = ImageListActivity.detailedImageHashMap

    override fun start() {
        parseFromWebSite()
    }

    override fun parseFromWebSite(): Disposable {
        val thumbImage = detailedImageActivity.detailedActivityExtra()

        return Flowable.create<DetailedImage>({
            var uri: String? = null
            var description: String? = null

            if (detailedImageHashMap.containsKey(thumbImage.href)) {
                detailedImageHashMap[thumbImage.href].let {
                    uri = it?.uri
                    description = it?.description
                }
            } else {
                Jsoup.connect(thumbImage.href)
                        .timeout(5000)
                        .get()
                        .body()
                        .select("img[class=\"innerImage\"], p:eq(4)")
                        .let {
                            for (element in it) {
                                if (!element.hasText()) uri = element.attr("abs:src")
                                else description = element.text()
                            }
                        }
            }

            it.onNext(DetailedImage(thumbImage.name, uri, description))
            it.onComplete()
        }, BackpressureStrategy.BUFFER)
                .doOnSubscribe {}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            detailedImageHashMap.put(thumbImage.href.toString(), it)

                            detailedImageActivity.apply {
                                setImageInImageView(it.uri)
                                setNameInTextView(it.name)
                                setDescriptionInTextView(it.description)
                            }
                        },
                        { Log.e("$javaClass [parseFromWebSite()] : ", "${it.printStackTrace()}") })
    }
}