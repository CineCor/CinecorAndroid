package com.cinecor.android

import com.cinecor.android.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class CinecorApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<CinecorApp> =
            DaggerApplicationComponent.builder().create(this)
}
