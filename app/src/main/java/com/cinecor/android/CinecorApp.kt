package com.cinecor.android

import com.cinecor.android.di.DaggerApplicationComponent
import com.google.firebase.database.FirebaseDatabase
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class CinecorApp : DaggerApplication() {

    @Inject lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate() {
        super.onCreate()
        setupFirebase()
    }

    private fun setupFirebase() {
        firebaseDatabase.setPersistenceEnabled(true)
    }

    override fun applicationInjector(): AndroidInjector<CinecorApp> {
        return DaggerApplicationComponent.builder().create(this)
    }
}
