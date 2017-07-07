package com.cinecor.android.di

import com.cinecor.android.CinecorApp
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        FragmentModule::class,
        ActivityModule::class,
        AppModule::class
))
interface ApplicationComponent : AndroidInjector<CinecorApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<CinecorApp>()
}
