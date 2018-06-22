package com.cinecor.android

import com.cinecor.android.di.DaggerAppComponent
import com.google.firebase.auth.FirebaseAuth
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class CinecorApp : DaggerApplication() {

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate() {
        super.onCreate()

        firebaseAuth.signInAnonymously()
    }

    override fun applicationInjector(): AndroidInjector<CinecorApp> =
            DaggerAppComponent.builder().create(this)
}
