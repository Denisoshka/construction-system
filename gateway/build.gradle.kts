import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
  java
  id("org.springframework.boot") version "3.4.3"
  id("io.spring.dependency-management") version "1.1.7"
}

group = "d.zhdanov.ccfit.nsu"
version = "0.0.1-SNAPSHOT"

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(23)
  }
}

repositories {
  mavenCentral()
}

extra["springCloudVersion"] = "2024.0.0"

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
  implementation("org.springframework.cloud:spring-cloud-starter-gateway")
  implementation("org.springframework.boot:spring-boot-starter-security")
  
//  implementation("io.micrometer:micrometer-tracing")
//  implementation("io.micrometer:micrometer-tracing-bridge-otel")
//  implementation("io.opentelemetry:opentelemetry-exporter-otlp")
//  implementation("io.opentelemetry.instrumentation:opentelemetry-spring-boot-starter")
//  implementation("io.opentelemetry:opentelemetry-sdk-extension-autoconfigure")
//  implementation(platform(SpringBootPlugin.BOM_COORDINATES))
//  implementation(platform("io.opentelemetry.instrumentation:opentelemetry-instrumentation-bom:2.14.0"))

//  implementation("io.opentelemetry.instrumentation:opentelemetry-instrumentation-bom:2.14.0")
//  implementation(platform("io.opentelemetry.instrumentation:opentelemetry-instrumentation-bom:2.14.0"))

  implementation("org.projectlombok:lombok")
  implementation(project(":utils"))
  
  compileOnly("org.projectlombok:lombok")
  annotationProcessor("org.projectlombok:lombok")
}

dependencyManagement {
  imports {
    mavenBom("io.opentelemetry.instrumentation:opentelemetry-instrumentation-bom:2.14.0")
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}
