package com.framgia.oleo.utils.extension

import android.content.Intent
import android.net.Uri
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.framgia.oleo.R
import com.framgia.oleo.utils.AnimateType

fun Fragment.replaceFragment(@IdRes containerId: Int, fragment: Fragment,
                             addToBackStack: Boolean = true, tag: String = fragment::class.java.simpleName,
                             animateType: AnimateType = AnimateType.FADE) {
    fragmentManager?.transact({
        if (addToBackStack) {
            addToBackStack(tag)
        }
        replace(containerId, fragment, tag)
    }, animateType = animateType)
}

fun Fragment.addFragment(@IdRes containerId: Int, fragment: Fragment,
                         addToBackStack: Boolean = true,
                         tag: String = fragment::class.java.simpleName,
                         animateType: AnimateType = AnimateType.FADE) {
    fragmentManager?.transact({
        if (addToBackStack) {
            addToBackStack(tag)
        }
        add(containerId, fragment, tag)
    }, animateType = animateType)
}

fun Fragment.goBackFragment(): Boolean {
    fragmentManager.notNull {
        val isShowPreviousPage = it.backStackEntryCount > 0
        if (isShowPreviousPage) {
            it.popBackStackImmediate()
        }
        return isShowPreviousPage
    }
    return false
}

fun Fragment.startActivityImplicit(uriIntent: String, data: String) {
    val intent = Intent(uriIntent)
    intent.data = Uri.parse("smsto:")
    intent.putExtra("sms_body", data)
    startActivity(intent)
}

/**
 * Runs a FragmentTransaction, then calls commitAllowingStateLoss().
 */
inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit,
                                    animateType: AnimateType = AnimateType.FADE) {
    beginTransaction().apply {
        setCustomAnimations(this, animateType)
        action()
    }.commitAllowingStateLoss()
}

fun setCustomAnimations(transaction: FragmentTransaction,
                        animateType: AnimateType = AnimateType.FADE) {
    when (animateType) {
        AnimateType.FADE -> transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out,
                R.anim.fade_in,
                R.anim.fade_out)
        AnimateType.SLIDE_TO_RIGHT -> transaction.setCustomAnimations(R.anim.slide_in_right,
                R.anim.slide_out_left, R.anim.slide_in_left,
                R.anim.slide_out_right)
        AnimateType.BOTTOM_UP -> TODO()
    }
}
