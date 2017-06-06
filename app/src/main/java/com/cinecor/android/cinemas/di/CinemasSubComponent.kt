package com.cinecor.android.cinemas.di

import com.cinecor.android.cinemas.ui.CinemasActivity

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(CinemasModule::class))
interface CinemasSubComponent : AndroidInjector<CinemasActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<CinemasActivity>()
}
