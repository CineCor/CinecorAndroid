package com.cinecor.android.cinemas.di

import android.support.v4.app.FragmentManager

import com.cinecor.android.cinemas.ui.CinemasActivity
import com.cinecor.android.cinemas.ui.CinemasAdapter

import dagger.Module
import dagger.Provides

@Module
class CinemasModule {

    @Provides
    internal fun provideFragmentAdapter(cinemasActivity: CinemasActivity): FragmentManager {
        return cinemasActivity.supportFragmentManager
    }

    @Provides
    internal fun provideAdapter(fragmentManager: FragmentManager): CinemasAdapter {
        return CinemasAdapter(fragmentManager)
    }
}
