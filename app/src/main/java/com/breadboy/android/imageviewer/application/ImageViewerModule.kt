package com.breadboy.android.imageviewer.application

import com.breadboy.android.imageviewer.ComponentBuilder
import com.breadboy.android.imageviewer.imagelist.view.ImageListActivity
import com.breadboy.android.imageviewer.imagelist.di.ImageListComponent
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by N4039 on 2017-09-15.
 */

@Module(subcomponents = arrayOf(ImageListComponent::class))
abstract class ImageViewerModule {

    @Binds
    @IntoMap
    @ImageViewerActivityKey(ImageListActivity::class)
    abstract fun bindImageListActivity(impl: ImageListComponent.Builder): ComponentBuilder<*, *>
}