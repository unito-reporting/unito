package com.unito

import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.StartParameter
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle

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

            }

            override fun projectsLoaded(gradle: Gradle) {

            }

            override fun buildStarted(gradle: Gradle) {

            }

            override fun projectsEvaluated(gradle: Gradle) {

            }
        })
    }

    private fun StartParameter.isUnitoEnabled() = systemPropertiesArgs.containsKey("unito")

}
