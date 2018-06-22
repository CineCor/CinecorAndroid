package com.cinecor.android.di

import android.content.Context
import com.cinecor.android.CinecorApp
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(application: CinecorApp): Context = application
}
