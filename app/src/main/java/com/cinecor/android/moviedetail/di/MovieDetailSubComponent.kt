package com.cinecor.android.moviedetail.di

import com.cinecor.android.cinemas.movies.di.MovieDetailModule
import com.cinecor.android.moviedetail.ui.MovieDetailActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(MovieDetailModule::class))
interface MovieDetailSubComponent : AndroidInjector<MovieDetailActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MovieDetailActivity>()
}
