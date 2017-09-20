package com.breadboy.android.imageviewer.data.remote.rx

import android.text.TextUtils
import com.breadboy.android.imageviewer.base.rx.BaseOnSubscribe
import com.breadboy.android.imageviewer.data.image.DetailedImage
import com.breadboy.android.imageviewer.data.image.ThumbImage
import io.reactivex.FlowableEmitter
import io.reactivex.FlowableOnSubscribe
import org.jsoup.Jsoup
import java.util.*

/**
 * Created by SDG on 2017. 9. 20..
 */

abstract class JsoupImageOnSubscribe<T> : BaseOnSubscribe<T> {

    override fun subscribe(emitter: FlowableEmitter<T>) {

        get("").let {
            emitter.onNext(it)
        }

        emitter.onComplete()
    }

    abstract fun get(url: String): T
}