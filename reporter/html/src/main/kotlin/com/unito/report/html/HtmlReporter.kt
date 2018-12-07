package com.unito.report.html

import com.unito.report.Reporter
import com.unito.report.ReporterContext
import java.io.File

class HtmlReporter : Reporter {

    override val name: String
        get() = "html"

    override fun render(context: ReporterContext) {
        val reportFile = File(context.outputDirectory, FILE_NAME)

        context.result.issues.forEach {
            reportFile.appendText("""[!] ${it.title} ${it.description}\n""")
        }
    }

    companion object {
        private const val FILE_NAME = "index.html"
    }
}
