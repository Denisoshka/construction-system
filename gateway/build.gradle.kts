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
  implementation("io.micrometer:micrometer-tracing-bridge-brave")
  implementation("org.springframework.cloud:spring-cloud-starter-gateway")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.projectlombok:lombok")
  compileOnly("org.projectlombok:lombok")
  annotationProcessor("org.projectlombok:lombok")
}

dependencyManagement {
  imports {
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}
