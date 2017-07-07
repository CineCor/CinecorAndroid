package com.cinecor.android.di

import com.cinecor.android.cinemas.movies.di.MoviesModule
import com.cinecor.android.cinemas.movies.ui.MoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector(modules = arrayOf(MoviesModule::class))
    abstract fun contributeMoviesFragmentInjector(): MoviesFragment
}
