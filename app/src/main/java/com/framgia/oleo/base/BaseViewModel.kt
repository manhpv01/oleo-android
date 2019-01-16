package com.framgia.oleo.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun launchDisposable(disposable: () -> Disposable) {
        compositeDisposable.add(disposable())
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
