package com.breadboy.android.imageviewer.intro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log

import com.breadboy.android.imageviewer.R
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class IntroActivity : IntroContract.View() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        Single.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            finish()

                            Log.e("!!!!!!!!!!!!!!!!!", "onNext")
                        },
                        {
                            Log.e("$javaClass [startIntroActivity()] : ", "${it.printStackTrace()}")
                        })
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fast_fade_in, R.anim.fast_fade_out)
    }

    fun activityOwnIntent(context: Context): Intent = Intent(context, javaClass)
}
