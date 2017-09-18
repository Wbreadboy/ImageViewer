package com.breadboy.android.imageviewer.base.view

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by SDG on 2017. 9. 16..
 */
abstract class BaseActivity<T> : AppCompatActivity() {

    protected val disposables by lazy { CompositeDisposable() }

    override fun onDestroy() {
        disposables.clear()

        super.onDestroy()
    }

    abstract fun setupActivityComponent()

    abstract fun activityOwnIntent(context: Context, T: Any): Any

    abstract fun startActivityFromIntent(intent: Intent)
}