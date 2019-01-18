package com.unito.report.html

import com.unito.report.Reporter
import com.unito.report.ReporterContext
import org.zeroturnaround.zip.ZipUtil.unpack
import java.io.File

class HtmlReporter : Reporter {

    override val name: String
        get() = "html"

    override fun render(context: ReporterContext) {
        val reportFile = File(context.outputDirectory, FILE_NAME)

        context.result.issues.forEach {
            reportFile.appendText("""[!] ${it.title} ${it.description}\n""")
        }

        val stream = javaClass.getResourceAsStream(WEB_ARCHIVE_RESOURCE_PATH)
        unpack(stream, context.outputDirectory)
    }

    companion object {
        private const val FILE_NAME = "data.json"
        private const val WEB_ARCHIVE_RESOURCE_PATH = "/resources/web-template.zip"
    }
}
