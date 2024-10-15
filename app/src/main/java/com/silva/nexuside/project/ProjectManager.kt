package com.silva.nexuside.project

import com.silva.util.FileUtil.*
import android.net.Uri
import java.io.File
import java.util.ArrayList

class ProjectManager {

    var LocationOfSave: File? = File(getExternalStorageDir(), "NexusIDEProjects")
    
    fun listOfProjects(): List<String> {
        val projectList = mutableListOf<String>()
        val temp = ArrayList<String>()
        listDir(LocationOfSave?.absolutePath, temp)
        
        temp.forEach { path ->
            if(isDirectory(path)) {
                projectList.add(path)
            }
        }
        return projectList
    }
    
}