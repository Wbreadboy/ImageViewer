package com.breadboy.android.imageviewer.application

import android.app.Activity
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by Shin, Dae Gyu on 2017-09-15.
 */

@MapKey
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ImageViewerActivityKey(val value: KClass<out Activity>)