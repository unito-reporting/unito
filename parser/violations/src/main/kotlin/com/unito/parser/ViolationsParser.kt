package com.unito.parser

import com.unito.model.Issue
import com.unito.model.Location
import com.unito.model.Severity
import se.bjurr.violations.lib.ViolationsApi
import se.bjurr.violations.lib.model.SEVERITY
import se.bjurr.violations.lib.reports.Parser
import java.io.File

class ViolationsParser : IssueParser {

    private val parsers = listOf(
        Parser.CHECKSTYLE to ".*/reports/detekt/.*\\.xml$",
        Parser.ANDROIDLINT to ".*/lint-results-.*\\.xml$"
    )

    override fun discoverIssues(projectDirectory: File): List<Issue> {
        val violations = parsers
            .flatMap {
                ViolationsApi
                    .violationsApi()
                    .findAll(it.first)
                    .withPattern(it.second)
                    .inFolder(projectDirectory.absolutePath)
                    .violations()
            }

        return violations.map { violation ->
            Issue(
                location = violation.file?.let { file ->
                    Location(file)
                },
                severity = violation.severity?.let {
                    when (it) {
                        SEVERITY.ERROR -> Severity.ERROR
                        SEVERITY.WARN -> Severity.WARNING
                        SEVERITY.INFO -> Severity.INFO
                    }
                },
                title = violation.rule,
                description = violation.message,
                reporter = violation.reporter
            )
        }
    }

}