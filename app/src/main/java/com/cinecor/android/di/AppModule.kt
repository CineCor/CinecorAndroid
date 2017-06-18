package com.cinecor.android.di

import android.content.Context

import com.cinecor.android.CinecorApp
import com.cinecor.android.cinemas.di.CinemasSubComponent
import com.cinecor.android.cinemas.movies.di.MoviesSubComponent
import com.cinecor.android.moviedetail.di.MovieDetailSubComponent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import dagger.Module
import dagger.Provides

@Module(subcomponents = arrayOf(CinemasSubComponent::class, MovieDetailSubComponent::class, MoviesSubComponent::class))
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
    fun provideFirebaseDatabaseCinemasReference(database: FirebaseDatabase): DatabaseReference {
        val reference = database.getReference("cinemas")
        reference.keepSynced(true)
        return reference
    }

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}
