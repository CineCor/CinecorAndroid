package com.cinecor.android

import com.cinecor.android.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class CinecorApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<CinecorApp> =
            DaggerAppComponent.builder().create(this)
}
