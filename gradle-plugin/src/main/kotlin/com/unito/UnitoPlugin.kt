package com.unito

import com.unito.model.Issue
import com.unito.model.Result
import com.unito.parser.ViolationsParser
import com.unito.report.html.HtmlReporter
import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import java.io.File

class UnitoPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val gradle = project.gradle

        if (!project.isUnitoEnabled()) {
            return
        }

        project.assertRootProject()
        project.assertNotExecuted()

        gradle.addBuildListener(object : BuildListener {
            override fun settingsEvaluated(settings: Settings) {

            }

            override fun buildFinished(result: BuildResult) {
                project.generateReport()
            }

            override fun projectsLoaded(gradle: Gradle) {

            }

            override fun buildStarted(gradle: Gradle) {

            }

            override fun projectsEvaluated(gradle: Gradle) {

            }
        })
    }

    private fun Project.generateReport() {
        val parser = ViolationsParser()
        val issues = mutableListOf<Issue>()
        project.allprojects.forEach {
            issues += parser.discoverIssues(it.buildDir)
        }

        val reporter = HtmlReporter()
        val reportDirectory = File(File(buildDir, "unito"), reporter.name)
        reportDirectory.mkdirs()

        reporter.render(Result(issues), reportDirectory)
    }

    private fun Project.isUnitoEnabled() = hasProperty("unito")

    private fun Project.assertNotExecuted() {
        if (state.executed) {
            throw GradleException("Unito plugin must be applied early in the build lifecycle")
        }
    }

    private fun Project.assertRootProject() {
        if (rootProject != this) {
            throw GradleException("Unito plugin can only be applied to the root project")
        }
    }
}
