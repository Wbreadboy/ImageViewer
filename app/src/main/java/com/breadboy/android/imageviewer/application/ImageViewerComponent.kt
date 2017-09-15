package com.breadboy.android.imageviewer.application

import dagger.Component
import javax.inject.Singleton

/**
 * Created by N4039 on 2017-09-15.
 */

@Singleton
@Component(modules = arrayOf(ImageViewerModule::class))
interface ImageViewerComponent {

    fun inject(imageViewerApplication: ImageViewerApplication): ImageViewerApplication
}