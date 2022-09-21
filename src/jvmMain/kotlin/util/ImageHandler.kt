package util

import java.io.File

class ImageHandler {

}

fun copySelectedImagesToDisk(selectedImages:List<LoadedImages>,sourceDir:String,folderToSave:String){

    val destDir = "/Users/lawrence/Pictures/StudioMate";

    selectedImages.forEach {
        val destPath = "${destDir}/${folderToSave}/${it.fileName}.CR2"
        val sourceFilePath = "${sourceDir}/${it.fileName}.CR2"

        File(sourceFilePath).copyTo(File(destPath),overwrite = true)
        println(destPath+"-----"+ sourceFilePath)
    }

}