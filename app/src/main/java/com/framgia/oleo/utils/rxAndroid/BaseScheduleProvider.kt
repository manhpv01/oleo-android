package com.framgia.oleo.utils.rxAndroid

import androidx.annotation.NonNull
import io.reactivex.Scheduler

interface BaseScheduleProvider {
    
    @NonNull
    fun io(): Scheduler
    
    @NonNull
    fun ui(): Scheduler
}
