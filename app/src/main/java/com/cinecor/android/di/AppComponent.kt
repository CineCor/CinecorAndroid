package com.cinecor.android.di

import com.cinecor.android.CinecorApp
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        ActivityModule::class,
        AppModule::class,
        FirebaseModule::class
))
interface AppComponent : AndroidInjector<CinecorApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<CinecorApp>()
}
