package com.cinecor.android.cinemas.movies.di

import com.cinecor.android.cinemas.movies.ui.MoviesAdapter
import com.cinecor.android.cinemas.movies.ui.MoviesFragment
import dagger.Module
import dagger.Provides

@Module
internal class MoviesModule {

    @Provides
    fun provideAdapter(fragment: MoviesFragment): MoviesAdapter {
        return MoviesAdapter(fragment)
    }
}
