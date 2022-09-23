package home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.AppButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import util.DsStoreFileFilter
import util.loadImagesFromDirectoryWithFlow
import javax.swing.JFileChooser
import javax.swing.UIManager

@Composable
fun FolderPicker(imageState: ImageState) {

    val scope = rememberCoroutineScope()
    var imageLoadingJob: Job? = null

    fun pickFolder() {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
        // /Users/lawrence/Pictures/Law/raws  System.getProperty("user.home","/")
        val fileChooser = JFileChooser( System.getProperty("user.home","/")).apply {
            fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
            dialogTitle = "Select a folder"
            approveButtonText = "Select"
            approveButtonToolTipText = "Select current directory"
        }
        fileChooser.showOpenDialog(null)


        val selectedDir = fileChooser.selectedFile

        if (selectedDir == null) {
            scope.launch {
                imageState.sendNotification("You did not select a valid directory")
            }
            // cancel operation
            return
        }

        // directory pick successfull
        imageState.imagesDirectory = selectedDir.absolutePath

        val job = scope.launch(Dispatchers.IO) {
            /* generate previews using exiftool CLI
                and wait for process to finish before reading from cache directory
             */
            try {
                //get num of files in dir
                val numOfFiles = selectedDir.listFiles(DsStoreFileFilter()).size
                imageState.totalImagesToLoad = numOfFiles
                val process: Process? = runCMD(selectedDir.absolutePath)
                process?.waitFor()

                /*val allImages = loadImagesFromDirectory(scope, imageState.cacheFolder)
                imageState.loadedImages.addAll(allImages)*/

                loadImagesFromDirectoryWithFlow(scope, imageState.cacheFolder).collect { item ->
                    imageState.loadedImages.add(item)

                }
                //imageState.loadedImages.addAll(allImages)

            } catch (e: Exception) {

                imageState.sendNotification(
                    e.message ?: "An error error occured while running command to process files"
                )

            }

        }
        imageLoadingJob = job

    }




    Column(
        Modifier.fillMaxWidth().fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            "Loading Photos... ${imageState.loadedImages.size} of ${imageState.totalImagesToLoad} ",
            color = Color.White,
            modifier = Modifier.padding(20.dp)
        )
        Button(
            onClick = {
                pickFolder()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(51, 51, 51)),

            border = BorderStroke(0.dp, Color.Transparent)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.border(0.dp, Color.Transparent).padding(20.dp)
            ) {
                Image(
                    painterResource("folder.png"), "sdsd",
                    modifier = Modifier.size(150.dp, 150.dp)
                )
                Text("Click here to Upload Folder", fontSize = 20.sp, color = Color.White)
            }
        }

        // show this button only if image loading is ongoing
        if (imageState.loadedImages.size != imageState.totalImagesToLoad) {

            AppButton(
                label = "Cancel Operation",
                onclick = {

                    scope.launch(Dispatchers.IO) {
                        imageLoadingJob?.cancel("operation cancelled")
                        imageState.sendNotification("Image loading operation cancelled")
                        imageState.loadedImages.clear()
                        imageState.totalImagesToLoad = 0
                    }
                }
            )
        }
    }
}