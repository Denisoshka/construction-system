plugins {
	id("java")
}

group = "d.zhdanov.ccfit.nsu"
version = "unspecified"

repositories {
	mavenCentral()
}

dependencies {
	implementation(libs.spring.cloud.gateway)
	implementation(libs.spring.cloud.starter.netflix.eureka.client)
	implementation(libs.spring.boot.oauth2.client)
	implementation(libs.gson)
	compileOnly(libs.lombok)
	annotationProcessor(libs.lombok)
	implementation(libs.spring.boot.actuator)
	implementation("org.springframework.boot:spring-boot-starter-actuator")
}

tasks.test {
	useJUnitPlatform()
}