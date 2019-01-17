package com.framgia.oleo.utils.di.module

import com.framgia.oleo.screen.home.HomeFragment
import com.framgia.oleo.screen.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment
}
