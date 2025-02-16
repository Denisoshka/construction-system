plugins {
	id("org.springframework.boot") version "3.4.2"
	id("com.google.cloud.tools.jib") version "3.4.4"


	id("java")
}

group = "d.zhdanov.ccfit.nsu"
version = "0.0.1-SNAPSHOT"

springBoot {
	mainClass = "d.zhdanov.ccfit.nsu.Eureka"
}
jib {
	from {
		image = "openjdk:23-jdk" // Образ с Java 23
	}
	to {
		image = "eureka"
	}

}
repositories {
	mavenCentral()
}

dependencies {
	implementation(libs.spring.cloud.starter.netflix.eureka.server)
	implementation(libs.gson)
	implementation(libs.spring.boot.actuator)
	implementation(libs.spring.boot.docker.compose)
}

