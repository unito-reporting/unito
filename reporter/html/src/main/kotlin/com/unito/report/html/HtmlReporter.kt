package com.unito.report.html

import com.google.gson.Gson
import com.unito.report.Reporter
import com.unito.report.ReporterContext
import com.unito.report.html.model.toJsonModel
import org.zeroturnaround.zip.ZipUtil.unpack
import java.io.File

class HtmlReporter : Reporter {

    override val name: String
        get() = "html"

    private val gson: Gson by lazy {
        Gson()
    }

    override fun render(context: ReporterContext) {
        val reportFile = File(context.outputDirectory, FILE_NAME)

        val jsonString = gson.toJson(context.result.toJsonModel()).toString()
        reportFile.writeText(jsonString)

        val stream = javaClass.getResourceAsStream(WEB_ARCHIVE_RESOURCE_PATH)
        unpack(stream, context.outputDirectory)
    }

    companion object {
        private const val FILE_NAME = "data.json"
        private const val WEB_ARCHIVE_RESOURCE_PATH = "/resources/web-template.zip"
    }
}
