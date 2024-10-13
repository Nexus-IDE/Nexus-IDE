plugins {
  id("com.android.library")
}

android {
  namespace = "com.silva.nexuside.resources"
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
    implementation(libs.androidx.annotation)
}
