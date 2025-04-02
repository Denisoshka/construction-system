plugins {
    id("java")
}

group = "d.zhdanov.ccfit.nsu"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
}

tasks.test {
    useJUnitPlatform()
}