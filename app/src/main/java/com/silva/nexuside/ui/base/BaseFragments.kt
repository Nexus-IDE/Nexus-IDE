package com.silva.nexuside.ui.base

import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceFragmentCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.color.MaterialColors
import com.google.android.material.transition.MaterialSharedAxis
import dev.chrisbanes.insetter.Insetter
import com.silva.nexuside.resources.Styles

open class BaseFragment() : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        reenterTransition =  MaterialSharedAxis(MaterialSharedAxis.X, false)
    }
    
    fun handleInsetts(rootView: View) {
        Insetter
            .builder()
            .padding(WindowInsetsCompat.Type.navigationBars())
            .applyToView(rootView)
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
        reenterTransition =  MaterialSharedAxis(MaterialSharedAxis.X, false)
    }
    
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(0, rootKey)
    }
    
    override fun toString(): String {
        return "BasePreferenceFragment"
    }
    
    fun handleInsetts(rootView: View) {
        Insetter
            .builder()
            .padding(WindowInsetsCompat.Type.navigationBars())
            .applyToView(rootView)
    }
}