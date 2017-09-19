package com.breadboy.android.imageviewer.intro

import android.app.Activity
import com.breadboy.android.imageviewer.base.presenter.BasePresenter

/**
 * Created by N4039 on 2017-09-19.
 */
interface IntroContract {

    abstract class View : Activity()

    interface Presenter : BasePresenter
}