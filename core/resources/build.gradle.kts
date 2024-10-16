plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
}

android {
  namespace = "com.silva.nexuside.resources"
  compileSdk = 34
  
  defaultConfig {
     minSdk = 26
     
     vectorDrawables.useSupportLibrary = true
  }
  
  compileOptions {
      sourceCompatibility = JavaVersion.VERSION_17
      targetCompatibility = JavaVersion.VERSION_17
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  buildFeatures { viewBinding = true }
}

materialThemeBuilder {
    themes {
        for ((name, color) in listOf(
            "Pyro" to "#EF7A35",
            "Indigo" to "#3F51B5",
            "Flamingo" to "#E91E63",
            "Mint" to "#009688",
            "Esmerald" to "#4CAF50",
            "Azure" to "#2196F3",
        )) {
            create(name) {
                primaryColor = color
                lightThemeFormat = "Theme.Nexus.%s.Light"
                lightThemeParent = "Theme.Nexus"
                darkThemeFormat = "Theme.Nexus.%s.Dark"
                darkThemeParent = "Theme.Nexus"

                isDynamicColors = false
            }
        }
    }

    generatePaletteAttributes = true
    packageName = "com.silva.nexuside.resources"
}

dependencies {
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.preference)
    implementation(libs.google.material)
}
