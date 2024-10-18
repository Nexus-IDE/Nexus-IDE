package com.silva.nexuside.ui.base

import android.os.Bundle
import android.view.View
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceFragmentCompat
import com.google.android.material.transition.MaterialSharedAxis
import dev.chrisbanes.insetter.Insetter

open class BaseFragment() : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
    }

    fun handleInsetts(rootView: View) {
        Insetter.builder().padding(WindowInsetsCompat.Type.navigationBars()).applyToView(rootView)
    }

    override fun toString(): String {
        return "BaseFragment"
    }
}

open class BasePreferenceFragment() : PreferenceFragmentCompat() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(0, rootKey)
    }

    override fun toString(): String {
        return "BasePreferenceFragment"
    }

    fun handleInsetts(rootView: View) {
        Insetter.builder().padding(WindowInsetsCompat.Type.navigationBars()).applyToView(rootView)
    }
}
