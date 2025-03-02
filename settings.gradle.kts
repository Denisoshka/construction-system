rootProject.name = "construction-organization-system"
pluginManagement {
  repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
  }
}

include(":gateway")
include(":router")
include(":report")
include(":service")
