package com.silva.nexuside.ui.base

import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceFragmentCompat
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.color.MaterialColors
import com.google.android.material.transition.MaterialSharedAxis

val sharedEnter = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {
    duration = 700
    interpolator = AccelerateDecelerateInterpolator()
}
val sharedExit = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {
    duration = 700
    interpolator = AccelerateDecelerateInterpolator()
}
val sharedReturn = MaterialSharedAxis(MaterialSharedAxis.X, false).apply {
    duration = 700
    interpolator = AccelerateDecelerateInterpolator()
}
val sharedReenter = MaterialSharedAxis(MaterialSharedAxis.X, false).apply {
    duration = 700
    interpolator = AccelerateDecelerateInterpolator()
}

open class BaseFragment() : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = sharedEnter
        returnTransition = sharedReturn
        exitTransition = sharedExit
        reenterTransition =  sharedReenter
    }
}

open class BasePreferenceFragment() : PreferenceFragmentCompat() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = sharedEnter
        returnTransition = sharedReturn
        exitTransition = sharedExit
        reenterTransition =  sharedReenter
    }
    
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(0, rootKey)
    }
}