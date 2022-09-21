package home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.skia.Bitmap
import org.jetbrains.skia.DirectContext
import org.jetbrains.skiko.toBufferedImage
import util.LoadedImages

@Composable
fun ThumbnailRowItem(imagesState:ImageState,index:Int,image:LoadedImages){

    Image(image.compressed,contentDescription = "ds",
        // contentScale = ContentScale.FillBounds,
        modifier = Modifier.
        rotate(270.0f)//.fillParentMaxHeight()

            .border(2.dp,Color.Transparent) .padding(5.dp)
            .clickable {
                imagesState.currentIndex = index
            }
    )
}



