package com.breadboy.android.imageviewer.data

/**
 * Created by SDG on 2017. 9. 20..
 */

interface ImageDataSource {

    fun loadThumbImages()

    fun loadDetailedImage()

    fun saveDetailedImage()

    fun deleteThumbImages()
}