package com.cinecor.android.cinemas.listMovies.di

import com.cinecor.android.cinemas.listMovies.ui.ListMoviesFragment

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(ListMoviesModule::class))
interface ListMoviesSubComponent : AndroidInjector<ListMoviesFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ListMoviesFragment>()
}
