package com.breadboy.android.imageviewer.detailedimage

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import com.breadboy.android.imageviewer.R
import com.breadboy.android.imageviewer.application.GlideApp
import com.breadboy.android.imageviewer.application.ImageViewerApplication
import com.breadboy.android.imageviewer.data.ThumbImage
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_detailed_image.*
import javax.inject.Inject

class DetailedImageActivity : DetailedImageContract.view() {

    @Inject
    lateinit var detailedImagePresenter: DetailedImagePresenter

    companion object {
        const val DETAILED_EXTRA = "detailed_extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detailed_image)
        setupActivityComponent()

        detailedImagePresenter.start()
    }

    override fun setupActivityComponent() {
        (ImageViewerApplication[this].getComponentBuilder(DetailedImageActivity::class.java) as DetailedImageComponent.Builder)
                .module(DetailedImageModule(this))
                .build()
                .injectMembers(this)
    }

    override fun activityOwnIntent(context: Context, T: Any): Intent = Intent(context, javaClass).putExtra(DETAILED_EXTRA, T as ThumbImage)

    override fun startActivityFromIntent(intent: Intent) {
        startActivity(intent)
    }

    fun detailedActivityExtra() = this.intent.getSerializableExtra(DETAILED_EXTRA) as ThumbImage

    fun setImageInImageView(uri: String?) {
        GlideApp.with(this)
                .load(uri)
                .placeholder(android.R.drawable.screen_background_light)
                .thumbnail(0.05f)
                .fitCenter()
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        goneProgressBar()

                        return false
                    }

                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        goneProgressBar()
                        Log.e("$javaClass [setImageInImageView()] : ", "${e?.printStackTrace()}")

                        return false
                    }
                })
                .error(R.drawable.glide_error)
                .into(imageview_image_detailed_image_activity)
    }

    fun setNameInTextView(name: String?) { textview_name_detailed_image_activity.text = name }

    fun setDescriptionInTextView(description: String?) { textview_description_detailed_image_activity.text = description }

    fun goneProgressBar() { progressbar_detail_image_activity.visibility = View.GONE }
}
