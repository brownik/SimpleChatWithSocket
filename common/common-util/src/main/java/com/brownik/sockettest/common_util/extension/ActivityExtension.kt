package com.brownik.sockettest.common_util.extension

import android.app.Activity
import android.os.Build
import android.view.WindowManager
import androidx.annotation.IdRes
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun Activity.setImmersiveMode() {
    window.setFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )

    if (Build.VERSION.SDK_INT >= 30)    // API 30이상 에 적용
        WindowCompat.setDecorFitsSystemWindows(window, false)

    val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
    windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    window.decorView.setOnApplyWindowInsetsListener { view, windowInsets ->
        windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())
        view.onApplyWindowInsets(windowInsets)
    }
}

fun FragmentActivity.addFragment(
    @IdRes containerId: Int,
    fragment: Fragment?,
    addBackStack: Boolean = false
) {
    requireNotNull(fragment)

    val transaction = supportFragmentManager.beginTransaction()
    transaction.add(containerId, fragment).apply {
        if (addBackStack) addToBackStack(null)
    }
    transaction.commitAllowingStateLoss()
}

fun FragmentActivity.replaceFragment(
    @IdRes containerId: Int,
    fragment: Fragment?,
    addBackStack: Boolean = false
) {
    requireNotNull(fragment)

    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(containerId, fragment).apply {
        if (addBackStack) addToBackStack(null)
    }
    transaction.commitAllowingStateLoss()
}

fun FragmentActivity.removeFragment(fragment: Fragment?) {
    if (fragment == null) return

    val transaction = supportFragmentManager.beginTransaction()
    transaction.remove(fragment)
    transaction.commitAllowingStateLoss()
}