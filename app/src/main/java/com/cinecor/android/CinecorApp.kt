package com.cinecor.android

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class CinecorApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<CinecorApp> =
            DaggerApplicationComponent.builder().create(this)
}
