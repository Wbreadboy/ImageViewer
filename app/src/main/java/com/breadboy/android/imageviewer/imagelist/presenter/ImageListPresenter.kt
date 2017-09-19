package com.breadboy.android.imageviewer.imagelist.presenter

import android.util.Log
import com.breadboy.android.imageviewer.data.image.ThumbImage
import com.breadboy.android.imageviewer.detailedimage.view.DetailedImageActivity
import com.breadboy.android.imageviewer.imagelist.ImageListContract
import com.breadboy.android.imageviewer.imagelist.view.ImageListActivity
import com.breadboy.android.imageviewer.intro.IntroActivity
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
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

    fun startIntroActivity(): Disposable {
        return Maybe.just(IntroActivity().activityOwnIntent(imageListActivity))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { imageListActivity.startActivity(it) },
                        { Log.e("$javaClass [startIntroActivity()] : ", "${it.printStackTrace()}") })
    }

    override fun parseFromWebSite(): Disposable {
        imageListActivity.visibleProgressBar()

        return Flowable.create<List<ThumbImage>>({
            var uri: String? = null
            var name: String? = null
            var href: String? = null

            Jsoup.connect("http://www.gettyimagesgallery.com/collections/archive/slim-aarons.aspx")
                    .timeout(5000)
                    .get()
                    .body()
                    .select("a[href^=\"/Picture-Library/Image.aspx?id=\"]")
                    .let {
                        for (element in it) {
                            if (!element.hasText()) {
                                uri = element.select("img").first().attr("abs:src")
                                href = element.attr("abs:href")
                            } else {
                                name = element.text()

                                mutableThumbImageList.add(ThumbImage(name, uri, href))
                            }
                        }
                    }

            it.onNext(mutableThumbImageList)
            it.onComplete()
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { imageListActivity.addImagesToRecyclerView(it.take(15)) },
                        { Log.e("$javaClass [parseFromWebSite()] : ", "${it.printStackTrace()}") },
                        {
                            imageListActivity.apply {
                                goneProgressBar()
                                goneSwipeRefreshLayout()
                                isMoreLoading = false
                            }
                        })
    }

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
                            },
                            {
                                imageListActivity.isMoreLoading = false

                                Log.e("$javaClass [loadMoreImages()] : ", "${it.printStackTrace()}")
                            },
                            { imageListActivity.isMoreLoading = false })

    fun clickedImageItem(item: ThumbImage) {
        imageListActivity.startActivityFromIntent(DetailedImageActivity().activityOwnIntent(imageListActivity, item))
    }
}