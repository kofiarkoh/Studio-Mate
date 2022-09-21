package util

import ImageConverter
import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.skia.Bitmap
import java.io.File


suspend fun loadImagesFromDirectory(scope:CoroutineScope):List<LoadedImages>{

    val folder = File("/Users/lawrence/Pictures/Law/mycache/two")
    val compressedfolder = File("/Users/lawrence/Pictures/Law/compressed")


    val imageBitmaps = mutableListOf<LoadedImages>()
    val listOfFiles = folder.listFiles()
    val imageConverter = ImageConverter(listOfFiles, 200, 600, "jpg")

    /*scope.launch {
        imageConverter.convertImages()
    }*/
    val job = scope.launch {
        listOfFiles.forEach {
            if(it.absolutePath.endsWith(".CR2") || it.absolutePath.endsWith(".jpg")){
                println(it.absolutePath)
                withContext(Dispatchers.IO) {
                   val resizedImage = imageConverter.getThumbnail(it)
                    val im = loadImageBitmap(it)
                    imageBitmaps.add(LoadedImages(resizedImage,
                        im, fileName = it.name.replace("_PreviewImage.jpg",""),
                        fileExt = it.extension,
                        filePath =  it.absolutePath
                    ))
                }
            }

        }
    }
    job.join()
    println("iamges size is ${imageBitmaps.size}")
    return imageBitmaps;
}

data class LoadedImages(
    val compressed:ImageBitmap,
    val raw:ImageBitmap,
    val fileName:String ="",
    val filePath:String = "",
    val fileExt:String = ""

)