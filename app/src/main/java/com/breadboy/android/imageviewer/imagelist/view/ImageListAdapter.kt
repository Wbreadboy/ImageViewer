package com.breadboy.android.imageviewer.imagelist.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.breadboy.android.imageviewer.R
import com.breadboy.android.imageviewer.application.GlideApp
import com.breadboy.android.imageviewer.base.view.BaseAdapter
import com.breadboy.android.imageviewer.base.view.BaseViewHolder
import com.breadboy.android.imageviewer.data.ThumbImage
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
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

        setImageInImageView(holder, position)
        setNameInTextView(holder, position)
    }

    private fun setImageInImageView(holder: ImageListViewHolder?, position: Int) {
        GlideApp.with(imageListActivity)
                .load(mutableItemList[position].uri)
                .fitCenter()
                .error(R.mipmap.ic_launcher)
                .into(holder?.imageListImageView)
    }

    private fun setNameInTextView(holder: ImageListViewHolder?, position: Int) {
        holder?.imageListTextView?.text = mutableItemList[position].name
    }

    override fun onItemClicked(holder: RecyclerView.ViewHolder?, position: Int, Item: ThumbImage) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}