package com.breadboy.android.imageviewer.imagelist.presenter

import android.util.Log
import com.breadboy.android.imageviewer.data.ThumbImage
import com.breadboy.android.imageviewer.imagelist.ImageListContract
import com.breadboy.android.imageviewer.imagelist.view.ImageListActivity
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import javax.inject.Inject

/**
 * Created by SDG on 2017. 9. 16..
 */
class ImageListPresenter

@Inject
constructor(val imageListActivity: ImageListActivity) : ImageListContract.Presenter {

    val mutableThumbImageList = mutableListOf<ThumbImage>()

    override fun start() {
        parseFromWebSite()
    }

    override fun parseFromWebSite() {
        Flowable.create<List<ThumbImage>>({ emitter ->
            Log.e("!!!!!!!!!!!!!!", "connecting...")
            val doc = Jsoup.connect("http://www.gettyimagesgallery.com/collections/archive/slim-aarons.aspx").get()
            Log.e("!!!!!!!!!!!!!!", "connect complete!")

            Log.e("!!!!!!!!!!!!!!", "selecting...")
            val imgUris = doc.body().select("a[href^=\"/Picture-Library/Image.aspx?id=\"]")
            Log.e("!!!!!!!!!!!!!!", "select complete!")

            imgUris.filter { it.hasText() }
                    .forEach { imgUri ->
                        imgUris.select("a[href=\"${imgUri.attr("href")}\"]").first()
                                .let {
                                    mutableThumbImageList.add(ThumbImage(imgUri.text(), it.select("img").attr("abs:src"), imgUri.attr("abs:href")))
                                }
                    }

            emitter.onNext(mutableThumbImageList)
            emitter.onComplete()
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    imageListActivity.setImageDataToRecyclerView(it.take(15))
                }, {
                    Log.e("$javaClass [parseFromWebSite()] : ", "${it.printStackTrace()}")
                })
    }

    fun loadMoreImageData() {

    }
}