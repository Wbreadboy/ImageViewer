package com.breadboy.android.imageviewer.detailedimage.di

import com.breadboy.android.imageviewer.ActivityScope
import com.breadboy.android.imageviewer.base.di.BaseModule
import com.breadboy.android.imageviewer.detailedimage.view.DetailedImageActivity
import com.breadboy.android.imageviewer.detailedimage.presenter.DetailedImagePresenter
import dagger.Module
import dagger.Provides

/**
 * Created by N4039 on 2017-09-18.
 */

@Module
class DetailedImageModule(var detailedImageActivity: DetailedImageActivity) : BaseModule<DetailedImageActivity>(detailedImageActivity) {

    @ActivityScope
    @Provides
    fun provideDetailedImageActivity() = detailedImageActivity

    @ActivityScope
    @Provides
    fun provideDetailedImagePresenter(detailedImageActivity: DetailedImageActivity) = DetailedImagePresenter(detailedImageActivity)
}