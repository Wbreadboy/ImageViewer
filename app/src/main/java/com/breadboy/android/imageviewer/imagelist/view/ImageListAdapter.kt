package com.breadboy.android.imageviewer.imagelist.view

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.breadboy.android.imageviewer.R
import com.breadboy.android.imageviewer.application.GlideApp
import com.breadboy.android.imageviewer.base.view.BaseAdapter
import com.breadboy.android.imageviewer.base.view.BaseViewHolder
import com.breadboy.android.imageviewer.data.ThumbImage
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.cardview_image_list.*
import javax.inject.Inject

/**
 * Created by SDG on 2017. 9. 16..
 */

class ImageListAdapter

@Inject
constructor(val imageListActivity: ImageListActivity): BaseAdapter<ImageListViewHolder, ThumbImage>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            ImageListViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.cardview_image_list, parent, false))

    override fun onBindViewHolder(holder: ImageListViewHolder?, position: Int) {
        holder?.bind()

        setViewInCardView(holder, position)
        setImageInImageView(holder, position)
        setNameInTextView(holder, position)
    }

    private fun setViewInCardView(holder: ImageListViewHolder?, position: Int) {
        holder?.imageListCardView?.setOnClickListener { onItemClickListener(mutableItemList[position]) } }

    private fun setImageInImageView(holder: ImageListViewHolder?, position: Int) {
        visibleProgressBar(holder)

        GlideApp.with(imageListActivity)
                .load(mutableItemList[position].uri)
                .placeholder(android.R.drawable.screen_background_light)
                .thumbnail(0.1f)
                .fitCenter()
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        goneProgressBar(holder)
                        return false
                    }

                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        goneProgressBar(holder)
                        Log.e("$javaClass [setImageInImageView()] : ", "${e?.printStackTrace()}")

                        return false
                    }
                })
                .error(R.drawable.glide_error)
                .into(holder?.imageListImageView)
    }

    private fun setNameInTextView(holder: ImageListViewHolder?, position: Int) { holder?.imageListTextView?.text = mutableItemList[position].name }

    private fun visibleProgressBar(holder: ImageListViewHolder?) { holder?.imageListProgressBar?.visibility = View.VISIBLE }

    private fun goneProgressBar(holder: ImageListViewHolder?) { holder?.imageListProgressBar?.visibility = View.GONE }

    override fun onItemClickListener(item: ThumbImage) { imageListActivity.imageListPresenter.clickedImageItem(item) }
}