package com.unito.report.html.model.json

internal class Location(
    val file: String,
    val lines: IntRange? = null,
    val startColumn: Int? = null,
    val endColumn: Int? = null,
    val module: Module? = null
)
