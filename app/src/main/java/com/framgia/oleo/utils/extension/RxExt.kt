package com.framgia.oleo.utils.extension

import com.framgia.oleo.utils.rxAndroid.BaseScheduleProvider
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Use SchedulerProvider configuration for Completable
 */
fun Completable.withScheduler(scheduler: BaseScheduleProvider): Completable =
    this.observeOn(scheduler.ui()).subscribeOn(scheduler.io())

/**
 * Use SchedulerProvider configuration for Single
 */
fun <T> Single<T>.withScheduler(scheduler: BaseScheduleProvider): Single<T> =
    this.observeOn(scheduler.ui()).subscribeOn(scheduler.io())

/**
 * Use SchedulerProvider configuration for Observable
 */
fun <T> Observable<T>.withScheduler(scheduler: BaseScheduleProvider): Observable<T> =
    this.observeOn(scheduler.ui()).subscribeOn(scheduler.io())

/**
 * Use SchedulerProvider configuration for Flowable
 */
fun <T> Flowable<T>.withScheduler(scheduler: BaseScheduleProvider): Flowable<T> =
    this.observeOn(scheduler.ui()).subscribeOn(scheduler.io())
