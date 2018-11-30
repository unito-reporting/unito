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

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(gradleApi())

    implementation(project(":core"))
    implementation(project(":reporter:html"))
    implementation(project(":parser:violations"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
