package com.breadboy.android.imageviewer.data.remote.rx

import com.breadboy.android.imageviewer.data.image.DetailedImage
import com.breadboy.android.imageviewer.data.image.ThumbImage
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.functions.Function
import org.jsoup.Jsoup

/**
 * Created by N4039 on 2017-09-20.
 */

class JsoupImageFlowable {

    fun <T : List<ThumbImage>> thumbImagesJsoup(function: Function<String, T>) =
            Flowable.create(object : JsoupImageOnSubscribe<T>() {
                override fun get(url: String) = function.apply(url)
            }, BackpressureStrategy.BUFFER)

    fun <T : DetailedImage> detailedImageJsoup(function: Function<String, T>) =
            Flowable.create(object : JsoupImageOnSubscribe<T>() {
                override fun get(url: String) = function.apply(url)
            }, BackpressureStrategy.BUFFER)
}