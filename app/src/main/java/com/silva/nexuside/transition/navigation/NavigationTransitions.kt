package com.silva.nexuside.transition.navigation

import com.google.android.material.transition.MaterialSharedAxis
import android.view.animation.AccelerateDecelerateInterpolator

object NavigationTransitions {

      const val enterTransition = MaterialSharedAxis(
          MaterialSharedAxis.Z,
          true
      ).apply {
         duration = 700
         interpolator = AccelerateDecelerateInterpolator()
      }
    
      const val exitTransition = MaterialSharedAxis(
          MaterialSharedAxis.Z,
          false
      ).apply {
         duration = 700
         interpolator = AccelerateDecelerateInterpolator()
      }
    
}
