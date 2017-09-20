package com.breadboy.android.imageviewer.data.local

import com.breadboy.android.imageviewer.data.ImageDataSource
import com.breadboy.android.imageviewer.data.image.DetailedImage
import com.breadboy.android.imageviewer.data.image.ThumbImage

/**
 * Created by SDG on 2017. 9. 20..
 */

class ImageLocalDataSource : ImageDataSource {

    companion object {
        val mutableThumbImageCache = mutableListOf<ThumbImage>()
        val mutableDetailedImageCache = mutableListOf<DetailedImage>()
    }

    override fun loadThumbImages() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadDetailedImage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveDetailedImage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteThumbImages() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}