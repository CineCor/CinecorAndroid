package com.cinecor.android.di

import android.content.Context
import com.cinecor.android.CinecorApp
import com.cinecor.android.common.viewmodel.CinemaViewModelFactory
import com.cinecor.android.data.source.CinemasRepository
import com.cinecor.android.data.source.local.CinecorDatabase
import com.cinecor.android.data.source.local.CinecorLocalDataSource
import com.cinecor.android.data.source.remote.CinecorRemoteDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(application: CinecorApp): Context = application

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseDatabase(): FirebaseDatabase = FirebaseDatabase.getInstance()

    @Provides
    fun provideLocalDatabase(context: Context): CinecorDatabase =
            CinecorDatabase.buildDatabase(context)

    @Provides
    fun provideLocalDataSource(database: CinecorDatabase): CinecorLocalDataSource =
            CinecorLocalDataSource(database.cinemaDao())

    @Provides
    fun provideRemoteDataSource(firebaseAuth: FirebaseAuth, firebaseDatabase: FirebaseDatabase): CinecorRemoteDataSource =
            CinecorRemoteDataSource(firebaseAuth, firebaseDatabase)

    @Provides
    fun provideRepository(localDataSource: CinecorLocalDataSource, remoteDataSource: CinecorRemoteDataSource): CinemasRepository =
            CinemasRepository(localDataSource, remoteDataSource)

    @Provides
    fun provideCinemaViewModelFactory(repository: CinemasRepository): CinemaViewModelFactory =
            CinemaViewModelFactory(repository)
}
