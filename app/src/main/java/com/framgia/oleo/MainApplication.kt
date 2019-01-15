package com.framgia.oleo

import android.app.Activity
import android.app.Application
import com.framgia.oleo.utils.GlideApp
import com.framgia.oleo.utils.di.AppInjector
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MainApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    private var currentClass: Class<*>? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppInjector.init(this)
        configLeakCanary()
    }

    override fun activityInjector(): AndroidInjector<Activity> =
        dispatchingAndroidInjector

    override fun onTrimMemory(level: Int) {
        GlideApp.get(this).onTrimMemory(level)
        super.onTrimMemory(level)
    }

    fun setCurrentClass(clazz: Class<out Activity>) {
        currentClass = clazz
    }

    fun getCurrentClass(): Class<*>? = currentClass

    private fun configLeakCanary() {
        if (BuildConfig.DEBUG) {
            if (LeakCanary.isInAnalyzerProcess(this)) return
            LeakCanary.install(this)
        }
    }

    companion object {
        lateinit var instance: MainApplication
    }
}
