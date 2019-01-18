package com.unito.report.html.model

import com.unito.model.Result
import com.unito.model.Issue
import com.unito.model.Location
import com.unito.model.Module
import com.unito.model.Severity
import com.unito.report.html.model.json.Location as JsonLocation
import com.unito.report.html.model.json.Issue as JsonIssue
import com.unito.report.html.model.json.Module as JsonModule
import com.unito.report.html.model.json.Severity as JsonSeverity
import com.unito.report.html.model.json.Result as JsonResult

internal fun Result.toJsonModel() = JsonResult(
    issues = issues.map { it.toJsonModel() }
)

internal fun Issue.toJsonModel() = JsonIssue(
    id = id,
    location = location?.toJsonModel(),
    severity = severity?.toJsonModel(),
    title = title,
    description = description,
    reporter = reporter
)

private fun Severity.toJsonModel() = when (this) {
    Severity.INFO -> JsonSeverity.INFO
    Severity.WARNING -> JsonSeverity.WARNING
    Severity.ERROR -> JsonSeverity.ERROR
}

private fun Location.toJsonModel() = JsonLocation(
    file = file,
    lines = lines,
    startColumn = startColumn,
    endColumn = endColumn,
    module = module?.toJsonModel()
)

private fun Module.toJsonModel(): JsonModule = JsonModule(
    name = name,
    parents = parents?.map { it.toJsonModel() }
)
