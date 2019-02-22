package com.framgia.oleo.utils.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.framgia.oleo.data.source.local.dao.UserDatabase
import com.framgia.oleo.data.source.local.dao.UserDatabase.Companion.DATABASE_NAME
import com.framgia.oleo.data.source.remote.MesssagesRemoteDataSource
import com.framgia.oleo.data.source.remote.api.middleware.BooleanAdapter
import com.framgia.oleo.data.source.remote.api.middleware.DoubleAdapter
import com.framgia.oleo.data.source.remote.api.middleware.IntegerAdapter
import com.framgia.oleo.utils.rxAndroid.BaseScheduleProvider
import com.framgia.oleo.utils.rxAndroid.SchedulerProvider
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context =
        application.applicationContext

    @Provides
    @Singleton
    fun provideBaseSchedulerProvider(): BaseScheduleProvider =
        SchedulerProvider.instance

    @Provides
    @Singleton
    fun provideUserLocalRepository(application: Application): UserDatabase {
        return Room.databaseBuilder(application.applicationContext, UserDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration().allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideMesssagesRemoteDataSource(): MesssagesRemoteDataSource {
        return MesssagesRemoteDataSource.newInstance()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        val booleanAdapter = BooleanAdapter()
        val integerAdapter = IntegerAdapter()
        val doubleAdapter = DoubleAdapter()
        return GsonBuilder()
            .registerTypeAdapter(Boolean::class.java, booleanAdapter)
            .registerTypeAdapter(Int::class.java, integerAdapter)
            .registerTypeAdapter(Double::class.java, doubleAdapter)
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }
}
