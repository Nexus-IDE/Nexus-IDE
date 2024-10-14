plugins {
  aliss(libs.plugins.android.library)
}

android {
  namespace = "com.silva.util"
  compileSdk = 34
  
  defaultConfig {
     minSdk = 26
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

dependencies {
  implementation(libs.androidx.appcompat)
  implementation(libs.google.material)
  implementation(libs.common.prdownloader)
}
