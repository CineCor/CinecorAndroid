package com.cinecor.android

import android.app.Activity
import android.app.Application
import com.cinecor.android.di.DaggerAppComponent
import com.facebook.drawee.backends.pipeline.Fresco
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class CinecorApp : Application(), HasActivityInjector {

    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var firebaseDatabase: FirebaseDatabase
    @Inject lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
        setupFirebase()
        setupFresco()
    }

    private fun injectDependencies() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

    private fun setupFirebase() {
        firebaseDatabase.setPersistenceEnabled(true)
        firebaseAuth.signInAnonymously()
    }

    private fun setupFresco() {
        Fresco.initialize(this)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }
}
