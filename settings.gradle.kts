pluginManagement {
   repositories {
      gradlePluginPortal()
      google()
      mavenCentral()
   }
}

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://jitpack.io")
  }
}

rootProject.name = "NexusIDE"

include(":app")

include(":feature:editor")

include(
    ":core:utils",
    ":core:resources"
)