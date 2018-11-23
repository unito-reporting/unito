import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

gradlePlugin {
    (plugins) {
        create("unito-gradle-plugin") {
            id = "unito"
            implementationClass = "com.unito.UnitoPlugin"
        }
    }
}

group = property("unito.group")!!
version = property("unito.version")!!

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":core"))
    implementation(gradleApi())
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
