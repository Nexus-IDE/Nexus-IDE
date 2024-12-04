
plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.materialthemebuilder)
}

android {
  namespace = "com.silva.nexuside"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.silva.nexuside"
    minSdk = 26
    targetSdk = 34
    versionCode = 1
    versionName = "0.1 beta"

    vectorDrawables.useSupportLibrary = true
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
  signingConfigs {
    getByName("debug") {
      storeFile = file(layout.buildDirectory.dir("../testkey.keystore"))
      storePassword = "testkey"
      keyAlias = "testkey"
      keyPassword = "testkey"
    }
  }
}

dependencies {
  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
  
  implementation(libs.androidx.annotation)
  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.preference)
  implementation(libs.androidx.datastore)
  implementation(libs.androidx.lifecycle.runtime)
  implementation(libs.androidx.lifecycle.viewmodel)
  coreLibraryDesugaring(libs.androidx.desugar)

  implementation(libs.google.material)
  implementation(libs.google.guava)
  implementation(libs.google.gson)
  
  implementation(libs.common.editor)
  implementation(libs.common.editor.textmate)
  implementation(libs.common.prdownloader)
  implementation(libs.common.utilcode)
  
  implementation(libs.glide.glide)
  implementation(libs.glide.compiler)
  
  implementation(libs.insetter)
  
  implementation(libs.koin.android)
  
  implementation(project(":feature:editor"))
  
  implementation(project(":core:utils"))
  implementation(project(":core:resources"))
}
