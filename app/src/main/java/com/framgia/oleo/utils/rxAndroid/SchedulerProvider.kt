package com.framgia.oleo.utils.rxAndroid

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProvider : BaseScheduleProvider {
    
    companion object {
        val instance: BaseScheduleProvider by lazy { SchedulerProvider() }
    }
    
    override fun io(): Scheduler = Schedulers.io()
    
    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
}
