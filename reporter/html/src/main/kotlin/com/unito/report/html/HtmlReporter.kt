package com.unito.report.html

import com.unito.model.Result
import com.unito.report.Reporter
import java.io.File

class HtmlReporter : Reporter {

    override val name: String
        get() = "html"

    override fun render(result: Result, outputDirectory: File) {
        val reportFile = File(outputDirectory, FILE_NAME)

        result.issues.forEach {
            reportFile.writeText("""${it.title} ${it.description}""")
        }
    }

    companion object {
        private const val FILE_NAME = "index.html"
    }
}
