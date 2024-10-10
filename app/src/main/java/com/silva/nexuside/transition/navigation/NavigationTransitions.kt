package com.silva.nexuside.transition.navigation

import com.google.android.material.transition.MaterialSharedAxis
import android.view.animation.AccelerateDecelerateInterpolator

class NavigationTransitions {

    companion object {
    
      val enterTransition: MaterialSharedAxis = MaterialSharedAxis(
          MaterialSharedAxis.Z,
          true
      ).apply {
         duration = 700
         interpolator = AccelerateDecelerateInterpolator()
      }
    
      val exitTransition: MaterialSharedAxis = MaterialSharedAxis(
          MaterialSharedAxis.Z,
          false
      ).apply {
         duration = 700
         interpolator = AccelerateDecelerateInterpolator()
      }
    
    }
    
}
