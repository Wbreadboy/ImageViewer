package com.breadboy.android.imageviewer.imagelist

import android.util.Log
import com.breadboy.android.imageviewer.base.presenter.BasePresenter
import com.breadboy.android.imageviewer.base.view.BaseActivity
import io.reactivex.disposables.Disposable
import org.jsoup.Jsoup

/**
 * Created by SDG on 2017. 9. 16..
 */
interface ImageListContract {

    abstract class View : BaseActivity<Presenter>()

    interface Presenter : BasePresenter {

        fun parseFromWebSite(): Disposable
    }
}