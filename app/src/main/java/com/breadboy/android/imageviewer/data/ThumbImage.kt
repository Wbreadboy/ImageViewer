package com.breadboy.android.imageviewer.data

import java.io.Serializable

/**
 * Created by N4039 on 2017-09-15.
 */

data class ThumbImage(
        var name: String?,
        var uri: String?,
        var href: String?) : Image, Serializable