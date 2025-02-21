rootProject.name = "construction-organization-system"
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        mavenCentral()
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        gradlePluginPortal()
    }
}

include(":gateway")
include(":router")
include(":report")
include(":service")
