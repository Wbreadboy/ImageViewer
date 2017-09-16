package com.breadboy.android.imageviewer.imagelist.di

import com.breadboy.android.imageviewer.ActivityScope
import com.breadboy.android.imageviewer.ComponentBuilder
import com.breadboy.android.imageviewer.base.di.BaseComponent
import com.breadboy.android.imageviewer.imagelist.view.ImageListActivity
import dagger.Subcomponent

/**
 * Created by SDG on 2017. 9. 16..
 */

@ActivityScope
@Subcomponent(modules = arrayOf(ImageListModule::class))
interface ImageListComponent : BaseComponent<ImageListActivity> {

    @Subcomponent.Builder
    interface Builder : ComponentBuilder<ImageListModule, ImageListComponent>
}