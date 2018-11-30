package com.unito

import org.gradle.api.Project
import org.gradle.api.UnknownDomainObjectException
import org.gradle.api.plugins.JavaPluginConvention
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPom
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.*
import kotlin.reflect.KProperty

fun Project.configurePublishing() {
    plugins.apply("maven-publish")

    val javaPlugin = try {
        the(JavaPluginConvention::class)
    } catch (exception: UnknownDomainObjectException) {
        null
    } ?: return

    val sourcesJar by tasks.creating(org.gradle.api.tasks.bundling.Jar::class) {
        classifier = "sources"
        from(javaPlugin.sourceSets["main"].allSource)
    }
    val javadocJar by tasks.creating(org.gradle.api.tasks.bundling.Jar::class) {
        classifier = "javadoc"
        from(javaPlugin.docsDir)
        dependsOn("javadoc")
    }

    configure<PublishingExtension> {
        publications {
            create("default", MavenPublication::class.java) {
                from(project.components["java"])
                artifact(sourcesJar)
                artifact(javadocJar)
            }
        }
        repositories {
            maven {
                name = "BuildDir"
                setUrl("${project.rootDir}/build/repository")
            }
        }
    }
}
