plugins {
  id("com.android.application")
}

android {
  namespace = "com.silva.nexuside"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.silva.nexuside"
    minSdk = 26
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    vectorDrawables.useSupportLibrary = true

    ndk {
      abiFilters += listOf("arm64-v8a", "x86_64", "armeabi-v7a")
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles("proguard-rules.pro")
    }
    debug {
      isMinifyEnabled = false
      proguardFiles("proguard-rules.pro")
    }
  }

  compileOptions {
      isCoreLibraryDesugaringEnabled = true
      sourceCompatibility = JavaVersion.VERSION_17
      targetCompatibility = JavaVersion.VERSION_17
      }

  packaging {
    resources.excludes.addAll(
      arrayOf("META-INF/README.md", "META-INF/CHANGES", "bundle.properties", "plugin.properties")
    )

    jniLibs { useLegacyPackaging = true }
  }

  lint { abortOnError = false }

  buildFeatures {
    viewBinding = true
    buildConfig = true
  }
}

dependencies {
  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
  
  implementation(libs.androidx.annotation)
  implementation(libs.androidx.appcompat)
  coreLibraryDesugaring(libs.androidx.desugar)

  implementation(libs.google.material)
  implementation(libs.google.guava)
  implementation(libs.google.gson)
  
  implementation(libs.common.editor)
  implementation(libs.common.editor.textmate)
  
  implementation(libs.glide.glide)
  implementation(libs.glide.compiler)
  
  implementation(project(":editor"))
  implementation(project(":util"))
}
