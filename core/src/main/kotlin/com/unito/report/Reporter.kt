package com.unito.report

import com.unito.model.Result
import java.io.File

interface Reporter {
    val name: String
    fun render(result: Result, outputDirectory: File)
}
