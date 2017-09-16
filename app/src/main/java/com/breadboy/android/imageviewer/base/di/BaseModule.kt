package com.breadboy.android.imageviewer.base.di

import android.app.Activity
import dagger.Module

/**
 * Created by N4039 on 2017-09-15.
 */

@Module
abstract class BaseModule<out A: Activity>(val activity: A)