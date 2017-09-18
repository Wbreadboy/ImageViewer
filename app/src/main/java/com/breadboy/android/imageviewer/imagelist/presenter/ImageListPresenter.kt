package com.breadboy.android.imageviewer.imagelist.presenter

import android.util.Log
import com.breadboy.android.imageviewer.data.DetailedImage
import com.breadboy.android.imageviewer.data.ThumbImage
import com.breadboy.android.imageviewer.detailedimage.view.DetailedImageActivity
import com.breadboy.android.imageviewer.imagelist.ImageListContract
import com.breadboy.android.imageviewer.imagelist.view.ImageListActivity
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URI
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

    override fun parseFromWebSite() =
        Flowable.create<List<ThumbImage>>({
            Jsoup.connect("http://www.gettyimagesgallery.com/collections/archive/slim-aarons.aspx")
                    .get()
                    .body()
                    .select("a[href^=\"/Picture-Library/Image.aspx?id=\"]")
                    .let {
                        it.filter { it.hasText() }
                          .forEach { imgUri ->
                              it.select("a[href=\"${imgUri.attr("href")}\"]")
                                .first()
                                .let {
                                    mutableThumbImageList.add(ThumbImage(imgUri.text(), it.select("img").attr("abs:src"), imgUri.attr("abs:href")))
                                }
                          }
                    }

            it.onNext(mutableThumbImageList)
            it.onComplete()
        }, BackpressureStrategy.BUFFER)
                .doOnSubscribe { imageListActivity.visibleProgressBar() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { imageListActivity.addImagesToRecyclerView(it.take(15)) },
                        { Log.e("$javaClass [parseFromWebSite()] : ", "${it.printStackTrace()}") },
                        { imageListActivity.goneProgressBar() })

    fun loadMoreImages(page: Long) =
            Flowable.fromIterable(mutableThumbImageList)
                    .buffer(15)
                    .elementAt(page)
                    .doOnSubscribe { imageListActivity.isMoreLoading = true }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {
                                imageListActivity.apply {
                                    addImagesToRecyclerView(it)
                                    isMoreLoading = false
                                }

                                Log.e("!!!!!!!!!!!!!!!!!", "page : $page")
                            },
                            {
                                imageListActivity.isMoreLoading = false

                                Log.e("$javaClass [loadMoreImages()] : ", "${it.printStackTrace()}")
                            },
                            { imageListActivity.isMoreLoading = false })

    fun clickedImageItem(item: ThumbImage) {
        imageListActivity.startActivity(DetailedImageActivity().activityOwnIntent(imageListActivity, item))
    }
}