package com.unito.model

class Location(
    val file: String,
    val lines: IntRange? = null,
    val startColumn: Int? = null,
    val endColumn: Int? = null,
    val module: Module? = null
)
