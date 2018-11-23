package com.unito.parser

import com.unito.model.Issue
import java.io.File

interface IssueParser {
    fun discoverIssues(projectDirectory: File): List<Issue>
}
