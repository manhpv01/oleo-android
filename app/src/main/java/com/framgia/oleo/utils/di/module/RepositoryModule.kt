package com.framgia.oleo.utils.di.module

import com.framgia.oleo.data.source.UserRepository
import com.framgia.oleo.data.source.local.UserLocalDataSource
import com.framgia.oleo.data.source.local.dao.UserDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserLocalRepository(userDatabase: UserDatabase): UserRepository {
        return UserRepository(UserLocalDataSource(userDatabase.userDAO()))
    }
}
