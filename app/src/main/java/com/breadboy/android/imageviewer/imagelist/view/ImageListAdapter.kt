package com.breadboy.android.imageviewer.imagelist.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.breadboy.android.imageviewer.R
import com.breadboy.android.imageviewer.base.view.BaseAdapter
import com.breadboy.android.imageviewer.base.view.BaseViewHolder
import com.breadboy.android.imageviewer.data.ThumbImage

/**
 * Created by SDG on 2017. 9. 16..
 */

class ImageListAdapter() : BaseAdapter<BaseViewHolder, ThumbImage>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            ImageListViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.cardview_image_list, parent, false))

    override fun onBindViewHolder(holder: BaseViewHolder?, position: Int) {
        holder?.bind()


    }

    override fun onItemClicked(holder: RecyclerView.ViewHolder?, position: Int, Item: ThumbImage) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}