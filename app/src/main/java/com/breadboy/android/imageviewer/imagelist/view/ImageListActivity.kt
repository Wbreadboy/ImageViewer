package com.breadboy.android.imageviewer.imagelist.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.breadboy.android.imageviewer.R
import com.breadboy.android.imageviewer.application.ImageViewerApplication
import com.breadboy.android.imageviewer.data.image.DetailedImage
import com.breadboy.android.imageviewer.data.image.ThumbImage
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

    companion object {
       val detailedImageHashMap = hashMapOf<String, DetailedImage>()
    }

    var page: Long = 1
    var isMoreLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_image_list)
        setSupportActionBar(toolbar_activity_imagelist)

        setupActivityComponent()

        imageListPresenter.startIntroActivity()

        setupSwipeRefreshLayout()
        setupRecyclerView()
        setupPresenter()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun activityOwnIntent(context: Context, T: Any) {}

    override fun startActivityFromIntent(intent: Intent) {
        startActivity(intent)
        overridePendingTransition(R.anim.fast_fade_in, R.anim.fast_fade_out)
    }

    override fun setupActivityComponent() {
        (ImageViewerApplication[this].getComponentBuilder(ImageListActivity::class.java) as ImageListComponent.Builder)
                .module(ImageListModule(this))
                .build()
                .injectMembers(this)
    }

    private fun setupSwipeRefreshLayout() {
        swiperefreshlayout_activity_imagelist.setOnRefreshListener(onRefreshImageListListener())
    }

    private fun onRefreshImageListListener() =
            SwipeRefreshLayout.OnRefreshListener {
                isMoreLoading = true
                clearDatasForRefresh()

                imageListPresenter.start()
            }

    private fun clearDatasForRefresh() {
        imageListAdapter.clearItem()
        imageListPresenter.mutableThumbImageList.clear()
        page = 1
    }

    private fun setupRecyclerView() {
        recyclerview_image_list_activity.apply {
            adapter = imageListAdapter
            layoutManager = imageListGridLayoutManager

            addOnScrollListener(onScrollAndMoreListener())
        }
    }

    private fun setupPresenter() = imageListPresenter.start()

    fun onScrollAndMoreListener() =
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if (isMoreLoading) return

                    imageListGridLayoutManager.apply {
                        if (childCount + findFirstVisibleItemPositions(null).first() + 5 >= itemCount) {
                            imageListPresenter.loadMoreImages(page++)
                        }
                    }
                }
    }

    fun addImagesToRecyclerView(imageDataList: List<ThumbImage>) { imageListAdapter.addAllItems(imageDataList) }

    fun visibleProgressBar() { progressbar_image_list_activity.visibility = View.VISIBLE }

    fun goneProgressBar() { progressbar_image_list_activity.visibility = View.GONE }

    fun goneSwipeRefreshLayout() { swiperefreshlayout_activity_imagelist.isRefreshing = false }
}
