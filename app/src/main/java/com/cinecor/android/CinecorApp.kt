package com.cinecor.android

import com.cinecor.android.di.AppModule
import com.cinecor.android.di.BuildersModule
import com.google.firebase.database.FirebaseDatabase
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject
import javax.inject.Singleton

class CinecorApp : DaggerApplication() {

    @dagger.Component(
            modules = arrayOf(AndroidInjectionModule::class, AppModule::class, BuildersModule::class)
    )

    @Singleton
    internal interface Component : AndroidInjector<CinecorApp> {
        @dagger.Component.Builder
        abstract class Builder : AndroidInjector.Builder<CinecorApp>()
    }

    override fun applicationInjector(): AndroidInjector<CinecorApp> {
        return DaggerCinecorApp_Component.builder().create(this)
    }

    @Inject lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate() {
        super.onCreate()
        setupFirebase()
    }

    private fun setupFirebase() {
        firebaseDatabase.setPersistenceEnabled(true)
    }
}
