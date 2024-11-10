plugins {
    kotlin("jvm") version "2.0.20"
    application
}

application {
    mainClass = "org.example.MainKt"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("com.github.ajalt.clikt:clikt:5.0.1")
    implementation("org.ini4j:ini4j:0.5.4")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}