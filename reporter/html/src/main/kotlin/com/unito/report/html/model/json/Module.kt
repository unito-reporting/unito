package com.unito.report.html.model.json

internal class Module(
    val name: String,
    val parents: List<Module>? = null // first parent is a root module
)
