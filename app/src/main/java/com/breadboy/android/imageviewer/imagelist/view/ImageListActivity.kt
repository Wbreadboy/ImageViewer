package com.breadboy.android.imageviewer.imagelist.view

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.Toolbar
import com.breadboy.android.imageviewer.R
import com.breadboy.android.imageviewer.application.ImageViewerApplication
import com.breadboy.android.imageviewer.base.view.BaseActivity
import com.breadboy.android.imageviewer.imagelist.ImageListContract
import com.breadboy.android.imageviewer.imagelist.di.ImageListComponent
import com.breadboy.android.imageviewer.imagelist.di.ImageListModule
import com.breadboy.android.imageviewer.imagelist.presenter.ImageListPresenter
import kotlinx.android.synthetic.main.activity_image_list.*
import kotlinx.android.synthetic.main.content_image_list.*
import javax.inject.Inject

class ImageListActivity : ImageListContract.View() {

    @Inject
    lateinit var imageListPresenter: ImageListPresenter

    @Inject
    lateinit var imageListAdapter: ImageListAdapter

    @Inject
    lateinit var imageListGridLayoutManager: StaggeredGridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_image_list)

        setupActivityComponent()
        setupRecyclerView()

        val toolbar = toolbar_activity_imagelist
        setSupportActionBar(toolbar)
    }

    override fun onResume() {
        super.onResume()

        imageListPresenter.start()
    }

    override fun setupActivityComponent() {
        (ImageViewerApplication[this].getComponentBuilder(ImageListActivity::class.java) as ImageListComponent.Builder)
                .module(ImageListModule(this))
                .build()
                .injectMembers(this)
    }

    private fun setupRecyclerView() {
        recyclerview_image_list_activity.apply {
            adapter = imageListAdapter
            layoutManager = imageListGridLayoutManager

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                }
            })
        }
    }
}
