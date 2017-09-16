package com.breadboy.android.imageviewer.base.di

import android.app.Activity
import dagger.MembersInjector

/**
 * Created by N4039 on 2017-09-15.
 */

interface BaseComponent<A: Activity> : MembersInjector<A>