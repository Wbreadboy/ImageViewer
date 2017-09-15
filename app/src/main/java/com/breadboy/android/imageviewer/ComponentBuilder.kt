package com.breadboy.android.imageviewer

import com.breadboy.android.imageviewer.base.BaseComponent
import com.breadboy.android.imageviewer.base.BaseModule

/**
 * Created by N4039 on 2017-09-15.
 */

interface ComponentBuilder<M: BaseModule<*>, C: BaseComponent<*>> {

    fun module(module: M): ComponentBuilder<M, C>
    fun build(): C
}