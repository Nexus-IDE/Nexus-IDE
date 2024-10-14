plugins {
  alias(libs.plugins.android.application)
}

android {
  namespace = "com.silva.editor"
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
  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

  implementation(libs.androidx.appcompat)
  implementation(libs.google.material)
  implementation(libs.google.guava)
  implementation(libs.google.gson)
  implementation(libs.common.editor)
  implementation(libs.common.editor.textmate)
  implementation(project(":util"))
}
