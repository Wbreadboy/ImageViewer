package com.breadboy.android.imageviewer.data.remote.rx

import com.breadboy.android.imageviewer.base.rx.BaseOnSubscribe
import io.reactivex.FlowableEmitter
import io.reactivex.FlowableOnSubscribe

/**
 * Created by SDG on 2017. 9. 20..
 */

abstract class JsoupImageOnSubscribe<T> : BaseOnSubscribe<T> {

    override fun subscribe(emitter: FlowableEmitter<T>) {

    }
}