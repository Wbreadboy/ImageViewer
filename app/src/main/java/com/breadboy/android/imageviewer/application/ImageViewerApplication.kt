package com.breadboy.android.imageviewer.application

import android.app.Activity
import android.app.Application
import android.content.Context
import com.breadboy.android.imageviewer.ComponentBuilder
import com.breadboy.android.imageviewer.WhichSubcomponentBuilders
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by Shin, Dae Gyu on 2017-09-15.
 */

class ImageViewerApplication : Application(), WhichSubcomponentBuilders {

    @Inject
    lateinit var ImageViewerComponentBuilder: Map<Class<out Activity>, @JvmSuppressWildcards Provider<ComponentBuilder<*, *>>>

    companion object {
        operator fun get(context: Context): WhichSubcomponentBuilders = context.applicationContext as WhichSubcomponentBuilders
    }

    override fun onCreate() {
        super.onCreate()

        setupImageViewerModule()
    }

    private fun setupImageViewerModule() = DaggerImageViewerComponent.create().inject(this)

    override fun getComponentBuilder(activity: Class<out Activity>) = ImageViewerComponentBuilder[activity]!!.get()
}
