package com.breadboy.android.imageviewer.detailedimage

import com.breadboy.android.imageviewer.ActivityScope
import com.breadboy.android.imageviewer.ComponentBuilder
import com.breadboy.android.imageviewer.base.di.BaseComponent
import dagger.Subcomponent

/**
 * Created by N4039 on 2017-09-18.
 */

@ActivityScope
@Subcomponent(modules = arrayOf(DetailedImageModule::class))
interface DetailedImageComponent : BaseComponent<DetailedImageActivity> {

    @Subcomponent.Builder
    interface Builder : ComponentBuilder<DetailedImageModule, DetailedImageComponent>
}