package util

import ImageConverter
import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File


suspend fun loadImagesFromDirectory(
    scope: CoroutineScope,
    cacheDirPath: String,
): List<LoadedImages> {


    val folder = File(cacheDirPath)

    val imageBitmaps = mutableListOf<LoadedImages>()
    val listOfFiles = folder.listFiles()

    val job = scope.launch {

        listOfFiles.forEach {
            println("found file ${it.absoluteFile}")
            if (it.absolutePath.endsWith(".CR2") || it.absolutePath.endsWith(".jpg")) {
                println(it.absolutePath)
                withContext(Dispatchers.IO) {
                    val resizedImage = ImageConverter.getThumbnail(it)
                    val im = loadImageBitmap(it)

                    imageBitmaps.add(
                        LoadedImages(
                            resizedImage,
                            im, fileName = it.name.replace("_PreviewImage.jpg", ""),
                            fileExt = it.extension,
                            filePath = it.absolutePath
                        )
                    )
                }
            }

        }
    }
    job.join()
    return imageBitmaps;
}

data class LoadedImages(
    val compressed: ImageBitmap,
    val raw: ImageBitmap,
    val fileName: String = "",
    val filePath: String = "",
    val fileExt: String = ""

)