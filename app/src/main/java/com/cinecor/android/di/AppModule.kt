package com.cinecor.android.di

import android.content.Context

import com.cinecor.android.CinecorApp
import com.cinecor.android.cinemas.di.CinemasSubComponent
import com.cinecor.android.cinemas.listMovies.di.ListMoviesSubComponent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

import dagger.Module
import dagger.Provides

@Module(subcomponents = arrayOf(CinemasSubComponent::class, ListMoviesSubComponent::class))
class AppModule {

    @Provides
    fun provideContext(application: CinecorApp): Context {
        return application.applicationContext
    }

    @Provides
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}
