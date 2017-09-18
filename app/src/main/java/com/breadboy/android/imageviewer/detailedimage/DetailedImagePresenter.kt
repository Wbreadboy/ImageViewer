package com.breadboy.android.imageviewer.detailedimage

import android.util.Log
import com.breadboy.android.imageviewer.base.presenter.BasePresenter
import com.breadboy.android.imageviewer.data.DetailedImage
import com.breadboy.android.imageviewer.data.ThumbImage
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

    lateinit var detailedImage: DetailedImage

    override fun start() {
        parseFromWebSite()
    }

    override fun parseFromWebSite(): Disposable {
        return Flowable.create<DetailedImage>({
            val thumbImage = detailedImageActivity.detailedActivityExtra()
            var uri: String? = null
            var description: String? = null

            Jsoup.connect(thumbImage.href)
                    .get()
                    .body().select("img[class=\"innerimage\"], p:contains(Photo by)")
                    .let {
                        for (element in it) {
                            if (!element.hasText()) uri = element.attr("abs:src")
                            else description = element.text()
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
                            detailedImageActivity.apply {
                                setImageInImageView(it.uri)
                                setNameInTextView(it.name)
                                setDescriptionInTextView(it.description)
                            }
                        },
                        { Log.e("$javaClass [parseFromWebSite()] : ", "${it.printStackTrace()}") },
                        {})
    }
}