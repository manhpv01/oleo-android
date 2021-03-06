package com.framgia.oleo.screen.main

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.framgia.oleo.R
import com.framgia.oleo.base.BaseActivity
import com.framgia.oleo.databinding.ActivityMainBinding
import com.framgia.oleo.screen.login.LoginFragment
import com.framgia.oleo.utils.Constant
import com.framgia.oleo.utils.extension.goBackFragment
import com.framgia.oleo.utils.extension.replaceFragmentInActivity
import com.framgia.oleo.utils.extension.showToast


class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var currentFragment: Fragment
    private var isDoubleTapBack = false
    private val loginFragment = LoginFragment.newInstance()
    private lateinit var inputMethodManager: InputMethodManager

    override fun onCreateView(savedInstanceState: Bundle?) {
        viewModel = MainViewModel.create(this, viewModelFactory)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewModel = viewModel
    }

    override fun setUpView() {
        inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun bindView() {
        replaceFragmentInActivity(R.id.containerMain, loginFragment)
        currentFragment = loginFragment
        supportActionBar?.hide()
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> if (currentFocus!! is EditText) {
                currentFocus!!.clearFocus()
                inputMethodManager.hideSoftInputFromWindow(
                        currentFocus!!.windowToken,
                        InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        }
        return super.dispatchTouchEvent(event)
    }

    override fun onBackPressed() {
        if (goBackFragment()) return
        if (isDoubleTapBack) {
            finish()
            return
        }
        isDoubleTapBack = true
        showToast(getString(R.string.click_again))
        Handler().postDelayed({
            isDoubleTapBack = false
        }, Constant.MAX_TIME_DOUBLE_CLICK_EXIT.toLong())
    }
}
