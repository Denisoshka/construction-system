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
//		maven ("https://repo.spring.io/libs-release" )

//		maven("https://repo.spring.io/release")

	}
}

include(":eureka")
include(":gateway")
include("router")
include("router")
include("api")
include("employees")
include("orders")
include("stuff")
include("system")
include("report")
