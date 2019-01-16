package com.framgia.oleo.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.framgia.oleo.MainApplication
import com.framgia.oleo.data.source.remote.api.error.RetrofitException
import com.framgia.oleo.utils.extension.notNull
import com.framgia.oleo.utils.extension.showToast
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateView(savedInstanceState)
        setUpView()
        bindView()
    }

    override fun onStart() {
        super.onStart()
        MainApplication.sInstance.setCurrentClass(javaClass)
    }

    fun onHandleError(error: RetrofitException?) {
        error?.getMessageError().notNull { showToast(it) }
    }

    protected abstract fun onCreateView(savedInstanceState: Bundle?)

    protected abstract fun setUpView()

    protected abstract fun bindView()

}
