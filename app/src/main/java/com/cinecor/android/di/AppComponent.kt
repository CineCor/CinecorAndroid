package com.cinecor.android.di


import com.cinecor.android.CinecorApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, AppModule::class, BuildersModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: CinecorApp): Builder

        fun build(): AppComponent
    }

    fun inject(app: CinecorApp)
}
