package home


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.FrameWindowScope
import java.awt.datatransfer.DataFlavor
import java.awt.dnd.DnDConstants
import java.awt.dnd.DropTarget
import java.awt.dnd.DropTargetDropEvent

@Composable
fun FullImagePreview(imagesState: ImageState) {

    val scope = rememberCoroutineScope()
    var imagePosition by remember { mutableStateOf(270.0f) }

    LaunchedEffect(Unit) {

    }


    Text("images are --- ${imagesState.loadedImages.size}")
    if ((imagesState.loadedImages.size != 0) && (imagesState.loadedImages.size == imagesState.totalImagesToLoad)) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { imagePosition -= 90f },

                ) {
                Icon(Icons.Filled.Refresh,
                    contentDescription = "Rotate Right",
                    tint = Color.White,
                    modifier = Modifier.size(50.dp).graphicsLayer {

                        rotationY = 180f

                    }
                )
            }
            Image(
                painter = painterResource("folder.png"), // BitmapPainter(imagesState.loadedImages[imagesState.currentIndex].raw),
                contentDescription = "contentDescription",
                contentScale = ContentScale.Fit,
                modifier = Modifier.width(500.dp).rotate(imagePosition).clip(RoundedCornerShape(5.dp))
            )

            IconButton(
                onClick = { imagePosition += 90f },

                ) {
                Icon(
                    Icons.Filled.Refresh,
                    contentDescription = "Rotate Right",
                    tint = Color.White,
                    modifier = Modifier.size(50.dp)
                )
            }
        }

    } else {
        FolderPicker(imagesState)
    }


}

@Composable
fun DropZone() {
    Column(Modifier.size(900.dp, 900.dp)) {
        Text("Drop Files Here To Proceed")
    }
    val target = object : DropTarget() {
        @Synchronized
        override fun drop(event: DropTargetDropEvent) {

            try {
                event.acceptDrop(DnDConstants.ACTION_REFERENCE)
                val droppedFiles = event.transferable.getTransferData(DataFlavor.javaFileListFlavor) as List<*>
                println(droppedFiles)
            } catch (e: java.lang.Exception) {
                println("Exception: ${e.message}")
            }
        }
    }

    ComposeWindow().contentPane.dropTarget = target


}

fun FrameWindowScope.setDropTarget() {


    val target = object : DropTarget() {
        @Synchronized
        override fun drop(event: DropTargetDropEvent) {

            try {
                event.acceptDrop(DnDConstants.ACTION_REFERENCE)
                val droppedFiles = event.transferable.getTransferData(DataFlavor.javaFileListFlavor) as List<*>
                println(droppedFiles)
            } catch (e: java.lang.Exception) {
                println("Exception: ${e.message}")
            }
        }
    }

}