package com.cinecor.android.cinemas.movies.di

import com.cinecor.android.cinemas.movies.ui.MoviesFragment

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(MoviesModule::class))
interface MoviesSubComponent : AndroidInjector<MoviesFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MoviesFragment>()
}
