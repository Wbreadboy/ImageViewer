package com.breadboy.android.imageviewer.base.view

import android.support.v7.widget.RecyclerView

/**
 * Created by SDG on 2017. 9. 16..
 */

abstract class BaseAdapter<VH: RecyclerView.ViewHolder, T> : RecyclerView.Adapter<VH>() {

    protected val mutableItemList: MutableList<T> = arrayListOf()

    fun addItem(item: T) = mutableItemList.add(item)

    fun addAllItems(items: List<T>) {
        val position = mutableItemList.size
        mutableItemList.addAll(items)
        notifyItemRangeInserted(position, items.size)
    }

    fun removeItem(item: T) = mutableItemList.remove(item)

    fun clearItem() = mutableItemList.clear()

    override fun getItemCount() = mutableItemList.count()

    abstract fun onItemClickListener(item: T)
}