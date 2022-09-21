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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import util.loadImagesFromDirectory
import javax.swing.JFileChooser
import javax.swing.UIManager

@Composable
fun FolderPicker(imageState: ImageState) {

    val scope = rememberCoroutineScope()

    fun pickFolder() {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
        val fileChooser = JFileChooser("/").apply {
            fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
            dialogTitle = "Select a folder"
            approveButtonText = "Select"
            approveButtonToolTipText = "Select current directory"
        }
        fileChooser.showOpenDialog(null)
        val selectedDir = fileChooser.selectedFile

        imageState.imagesDirectory = selectedDir.absolutePath

        scope.launch(Dispatchers.IO) {
            /* generate previews using exiftool CLI
                and wait for process to finish before reading from cache directory
             */
            val process: Process? = runCMD(selectedDir.absolutePath)
            process?.waitFor()

            val allImages = loadImagesFromDirectory(scope, imageState.cacheFolder)
            imageState.loadedImages.addAll(allImages)
        }

    }
    Column(
        Modifier.fillMaxWidth().fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = {
                pickFolder()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue.copy(.6f)),

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

    }
}