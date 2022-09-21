package home



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import util.AsyncImage
import util.loadImageBitmap
import util.loadImagesFromDirectory
import java.io.File

@Composable
fun FullImagePreview(imagesState:ImageState){
   var images = remember { mutableStateListOf<ImageBitmap>() }
   val scope = rememberCoroutineScope()
  //  val imagesState = rememberImageState()

   LaunchedEffect(Unit){

    // scope.launch { runCMD() }
      scope.launch {

      val allImages =   loadImagesFromDirectory(scope)
         imagesState.loadedImages.addAll(allImages)
      }
      /*scope.launch {
        val im = loadImageBitmap(File("/Users/lawrence/Pictures/Law/IMG_0776.jpg"))
         images.add(im)
      }*/
   }

   Text("images are --- ${imagesState.loadedImages.size}")
   if(imagesState.loadedImages.size != 0 ){
      Image(
         painter = BitmapPainter(imagesState.loadedImages[imagesState.currentIndex].raw),
         contentDescription = "contentDescription",
         contentScale = ContentScale.Fit,
         modifier = Modifier.width(500.dp).rotate(270.0f)
      )
   }


}