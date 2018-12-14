import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
