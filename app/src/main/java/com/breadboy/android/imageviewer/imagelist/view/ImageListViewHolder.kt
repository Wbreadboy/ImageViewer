package com.breadboy.android.imageviewer.imagelist.view

import android.support.v7.widget.CardView
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.breadboy.android.imageviewer.base.view.BaseViewHolder
import kotlinx.android.synthetic.main.cardview_image_list.view.*

/**
 * Created by SDG on 2017. 9. 16..
 */
class ImageListViewHolder(itemView: View) : BaseViewHolder(itemView) {

    lateinit var imageListCardView: CardView
    lateinit var imageListImageView: ImageView
    lateinit var imageListTextView: TextView
    lateinit var imageListProgressBar: ProgressBar

    override fun bind() = with(itemView) {
        imageListCardView = cardview_imagelist
        imageListImageView = imageview_image_cardview_imagelist
        imageListTextView = textview_name_cardview_imagelist
        imageListProgressBar = progressbar_cardview_imagelist
    }
}