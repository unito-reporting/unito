package com.unito.report

interface Reporter {
    val name: String
    fun render(context: ReporterContext)
}
