package com.silva.nexuside.project

import com.silva.nexuside.enums.ProjectTemplates
import java.io.File

data class Project(
    var template: ProjectTemplates? = null,
    var appName: String? = null,
    var packageName: String? = null,
    var path: File? = null,
    var language: String? = null,
    var minSdk: Int = 21,
)
