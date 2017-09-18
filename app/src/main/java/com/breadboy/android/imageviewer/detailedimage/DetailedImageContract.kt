package com.breadboy.android.imageviewer.detailedimage

import com.breadboy.android.imageviewer.base.presenter.BasePresenter
import com.breadboy.android.imageviewer.base.view.BaseActivity
import io.reactivex.disposables.Disposable

/**
 * Created by N4039 on 2017-09-18.
 */
interface DetailedImageContract {

    abstract class view : BaseActivity<Presenter>()

    interface Presenter: BasePresenter {

        fun parseFromWebSite(): Disposable
    }
}