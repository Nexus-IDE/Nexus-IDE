package com.silva.nexuside.project

import com.silva.util.FileUtil.*
import android.net.Uri
import java.io.File

class ProjectManager {

    var LocationOfSave: File? = File(getExternalStorageDir(), "NexusIDEProjects")
    
    fun listOfProjects(): List<String> {
        val projectList = List<String>()
        val temp = listDir(LocationOfSave?.absolutePath)
        
        temp.forEach { path ->
            if(path.isDirectory) {
                projectList.add(path)
            }
        }
        return projectList
    }
    
}