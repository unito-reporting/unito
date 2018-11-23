package com.unito

import com.unito.model.Result
import com.unito.parser.ViolationsParser
import com.unito.report.html.HtmlReporter
import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.StartParameter
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import java.io.File

class UnitoPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val gradle = project.gradle

        if (!gradle.startParameter.isUnitoEnabled()) {
            return
        }

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
        val issues = parser.discoverIssues(rootDir)
        val reporter = HtmlReporter()
        val reportDirectory = File(File(buildDir, "unito"), reporter.name)
        reportDirectory.mkdirs()
        reporter.render(Result(issues), reportDirectory)
    }

    private fun StartParameter.isUnitoEnabled() = systemPropertiesArgs.containsKey("unito")

}
