package com.breadboy.android.imageviewer.imagelist

import com.breadboy.android.imageviewer.base.presenter.BasePresenter
import com.breadboy.android.imageviewer.base.view.BaseActivity

/**
 * Created by SDG on 2017. 9. 16..
 */
interface ImageListContract {

    abstract class View : BaseActivity<Presenter>()

    interface Presenter : BasePresenter
}