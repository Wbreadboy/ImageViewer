package com.breadboy.android.imageviewer.base.view

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by SDG on 2017. 9. 16..
 */
abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind()
}