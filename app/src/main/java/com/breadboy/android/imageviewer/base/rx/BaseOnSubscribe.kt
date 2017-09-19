package com.breadboy.android.imageviewer.base.rx

import io.reactivex.FlowableEmitter
import io.reactivex.FlowableOnSubscribe

/**
 * Created by SDG on 2017. 9. 20..
 */

interface BaseOnSubscribe<T> : FlowableOnSubscribe<T> {

    override fun subscribe(emitter: FlowableEmitter<T>)
}