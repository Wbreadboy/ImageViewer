package com.breadboy.android.imageviewer.base.view

import android.support.v7.widget.RecyclerView

/**
 * Created by SDG on 2017. 9. 16..
 */

abstract class BaseAdapter<VH: RecyclerView.ViewHolder, T> : RecyclerView.Adapter<VH>() {

    protected val mutableItemList: MutableList<T> = arrayListOf()

    fun addItem(item: T) = mutableItemList.add(item)

    fun addAllItems(items: List<T>) {
        mutableItemList.size.let {
            mutableItemList.addAll(items)
            notifyItemRangeInserted(it, items.size)
        }
    }

    fun removeItem(item: T) = mutableItemList.remove(item)

    fun clearItem() {
        mutableItemList.size.let {
            mutableItemList.clear()
            notifyItemRangeRemoved(0, it)
        }
    }

    override fun getItemCount() = mutableItemList.count()

    abstract fun onItemClickListener(item: T)
}