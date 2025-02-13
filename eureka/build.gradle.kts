plugins {
    id("java")
}

group = "d.zhdanov.ccfit.nsu"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.spring.cloud.starter.netflix.eureka.server)
}

tasks.test {
    useJUnitPlatform()
}