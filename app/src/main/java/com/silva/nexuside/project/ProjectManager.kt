package com.silva.nexuside.project

import java.util.ArrayList
import com.silva.util.FileUtil.*
import android.net.Uri
import java.io.File

class ProjectManager {

    var LocationOfSave: File? = File(getExternalStorageDir(), "NexusIDEProjects")
    
    fun listOfProjects(): List<String> {
        val projectList = ArrayList<String>()
        val temp = listDir(LocationOfSave.toString())
        
        for (path in temp) {
            if(path.isDirectory) {
                projectList.add(path)
            }
        }
        return projectList
    }
    
}