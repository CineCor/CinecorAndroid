package com.cinecor.android.di

import com.cinecor.android.cinemas.di.CinemasModule
import com.cinecor.android.cinemas.movies.di.MovieDetailModule
import com.cinecor.android.cinemas.ui.CinemasActivity
import com.cinecor.android.moviedetail.ui.MovieDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = arrayOf(CinemasModule::class))
    abstract fun contributeCinemasActivityInjector(): CinemasActivity

    @ContributesAndroidInjector(modules = arrayOf(MovieDetailModule::class))
    abstract fun contributeMovieDetailActivityInjector(): MovieDetailActivity
}
