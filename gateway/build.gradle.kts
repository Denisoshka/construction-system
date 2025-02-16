plugins {
	id("java")

}
val netflixDgsVersion by extra("10.0.3")

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
//	implementation("com.netflix.graphql.dgs:graphql-dgs-spring-graphql-starter")
	compileOnly(libs.lombok)
	annotationProcessor(libs.lombok)
	implementation(libs.spring.boot.actuator)
	runtimeOnly(libs.spring.boot.docker.compose)
}

tasks.test {
	useJUnitPlatform()
}