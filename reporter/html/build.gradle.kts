import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

dependencies {
    api(kotlin("stdlib-jdk8"))
    api(project(":core"))
    api(project(":core"))
    implementation("org.zeroturnaround:zt-zip:1.13")
}

sourceSets {
    main {
        output.dir(
            mapOf("builtBy" to "generateWebInResources"),
            "$buildDir/generated/src/java/"
        )
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks {
    val compileWeb by creating(Exec::class) {
        workingDir = File("web")
        commandLine = listOf("npm", "run", "build")
    }

    val zipWeb by creating(Zip::class) {
        from(File("web", "build"))
        archiveName = "web-template.zip"
        destinationDir = project.buildDir
    }

    val generateWebInResources by creating(Copy::class) {
        from(File(project.buildDir, "web-template.zip"))
        into("$buildDir/generated/src/java/resources/")
    }

    zipWeb.dependsOn(compileWeb)
    generateWebInResources.dependsOn(zipWeb)
}
