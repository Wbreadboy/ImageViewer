package com.breadboy.android.imageviewer.imagelist.di

import android.support.v7.widget.StaggeredGridLayoutManager
import com.breadboy.android.imageviewer.ActivityScope
import com.breadboy.android.imageviewer.base.di.BaseModule
import com.breadboy.android.imageviewer.data.DetailedImage
import com.breadboy.android.imageviewer.imagelist.presenter.ImageListPresenter
import com.breadboy.android.imageviewer.imagelist.view.ImageListActivity
import com.breadboy.android.imageviewer.imagelist.view.ImageListAdapter
import dagger.Module
import dagger.Provides

/**
 * Created by SDG on 2017. 9. 16..
 */

@Module
class ImageListModule(var imageListActivity: ImageListActivity) : BaseModule<ImageListActivity>(imageListActivity) {

    @ActivityScope
    @Provides
    fun provideImageListActivity() = imageListActivity

    @ActivityScope
    @Provides
    fun provideImageListPresenter(imageListActivity: ImageListActivity) = ImageListPresenter(imageListActivity)

    @ActivityScope
    @Provides
    fun provideImageListAdapter(imageListActivity: ImageListActivity) = ImageListAdapter(imageListActivity)

    @ActivityScope
    @Provides
    fun provideImageListGridLayoutManager() = StaggeredGridLayoutManager(2, 1)
}