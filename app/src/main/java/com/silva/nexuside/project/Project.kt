package com.silva.nexuside.project

import com.silva.nexuside.enums.WizardTemplates
import java.io.File

data class Project(
    var template: WizardTemplates? = null,
    var appName: String? = null,
    var packageName: String? = null,
    var appPath: File? = null,
    var language: String? = null,
    var minSdk: Int = 21
)