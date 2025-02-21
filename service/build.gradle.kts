plugins {
    java
    alias(libs.plugins.dgs.codegen)
}

group = "d.zhdanov.ccfit.nsu"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.spring.boot.web)
    implementation(libs.dgs)
    implementation(libs.spring.boot.jdbc)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    runtimeOnly(libs.postgresql)
    runtimeOnly(libs.dgs.extended.scalars)
    implementation(libs.mapstruct.impl)
    annotationProcessor(libs.mapstruct.annotationprocessor)
}

tasks.test {
    useJUnitPlatform()
}

tasks.generateJava {
    schemaPaths.add("${projectDir}/src/main/resources/graphql")  // Путь к схеме GraphQL
    packageName = "d.zhdanov.graphql"  // Пакет для сгенерированных классов
    generateClient = true  // Включаем генерацию клиентских классов
}