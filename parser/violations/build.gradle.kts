import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("se.bjurr.violations:violations-lib:1.73")
    compile(project(":core"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
