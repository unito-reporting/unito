package com.unito.model

class Module(
    val name: String,
    val parents: List<Module>? = null // first parent is a root module
)
