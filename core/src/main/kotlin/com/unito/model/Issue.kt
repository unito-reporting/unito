package com.unito.model

class Issue(
    val id: String,
    val location: Location? = null,
    val severity: Severity? = null,
    val title: String? = null,
    val description: String? = null,
    val reporter: String
)
