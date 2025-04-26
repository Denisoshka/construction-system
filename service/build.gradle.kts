plugins {
  java
  alias(libs.plugins.spring.boot)
  alias(libs.plugins.dgs.codegen)
  alias(libs.plugins.dependency.management)
}

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(23)
  }
}

group = "d.zhdanov.ccfit.nsu"
version = "unspecified"

repositories {
  mavenCentral()
}

dependencies {
  implementation(libs.spring.boot.web)
  implementation(libs.netflix.dgs)
  implementation(libs.spring.boot.jdbc)
  implementation(libs.mapstruct.impl)
  implementation(libs.spring.boot.security)
  testImplementation(libs.spring.boot.test)
  testImplementation("com.netflix.graphql.dgs:dgs-starter-test")
//  implementation(libs.caffeine)
  implementation(libs.spring.boot.actuator)
  implementation(project(":utils"))
  compileOnly(libs.lombok)
  
  runtimeOnly(libs.postgresql)
  runtimeOnly(libs.netflix.dgs.extended.scalars)
  
  annotationProcessor(libs.lombok)
  annotationProcessor(libs.mapstruct.annotationprocessor)
  runtimeOnly("org.springframework.boot:spring-boot-docker-compose")
}
extra["netflixDgsVersion"] = "10.0.4"

dependencyManagement {
  imports {
    mavenBom(
      "com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:${
        property(
          "netflixDgsVersion"
        )
      }"
    )
  }
}

tasks.test {
  useJUnitPlatform()
}

tasks.generateJava {
  schemaPaths.add("${projectDir}/src/main/resources/schema")
  packageName = "d.zhdanov.graphql"
  generateClient = false
  generateClientv2 = false
}

tasks.withType<JavaCompile> {
  options.compilerArgs.add("-parameters")
}
