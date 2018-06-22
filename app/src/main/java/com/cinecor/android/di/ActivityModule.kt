package com.cinecor.android.di

import com.cinecor.android.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun contributeMainActivityInjector(): MainActivity
}
