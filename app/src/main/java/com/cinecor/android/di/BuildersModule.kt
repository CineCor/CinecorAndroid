package com.cinecor.android.di

import android.app.Activity
import android.support.v4.app.Fragment
import com.cinecor.android.cinemas.di.CinemasSubComponent
import com.cinecor.android.cinemas.movies.di.MoviesSubComponent
import com.cinecor.android.cinemas.movies.ui.MoviesFragment
import com.cinecor.android.cinemas.ui.CinemasActivity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

/**
 * This module contains all the binding to the sub component builders in the app
 */
@Module
abstract class BuildersModule {

    @Binds
    @IntoMap
    @ActivityKey(CinemasActivity::class)
    internal abstract fun bindCinemasActivityInjectorFactory(builder: CinemasSubComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @FragmentKey(MoviesFragment::class)
    internal abstract fun bindMoviesFragmentInjectorFactory(builder: MoviesSubComponent.Builder): AndroidInjector.Factory<out Fragment>

}
