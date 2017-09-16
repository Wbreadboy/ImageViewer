package com.breadboy.android.imageviewer.base.view

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
}