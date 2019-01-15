package com.framgia.oleo.utils.di.component

import android.app.Application
import com.framgia.oleo.MainApplication
import com.framgia.oleo.utils.di.module.ActivityBuildersModule
import com.framgia.oleo.utils.di.module.AppModule
import com.framgia.oleo.utils.di.module.NetworkModule
import com.framgia.oleo.utils.di.module.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ActivityBuildersModule::class]
)

interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(mainApplication: MainApplication)
}
