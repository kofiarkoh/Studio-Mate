package home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.sun.jdi.connect.LaunchingConnector
import components.FolderNameDialog

@Composable
fun HomeLayout(){

    val imagesState = rememberImageState()

    Column(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {

        Row(modifier = Modifier.weight(.75f,true).border(2.dp, Color.Transparent)){

            Column(
                modifier = Modifier.weight(.7f,true)
                    .border(2.dp, Color.Black)
                  //  .background(Color.Black)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                FullImagePreview(imagesState)
            }
            Column(
                modifier = Modifier.weight(.3f,true)
                    .fillMaxHeight()
            ) {
                RightSideBar(imagesState)
            }

        }

        LazyRow(
            modifier = Modifier
                .border(0.dp, Color.Yellow)
                .weight(.25f,true).fillMaxHeight().fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 25.dp)

        ) {

            itemsIndexed(imagesState.loadedImages) {i,image->

                //ThumbnailRowItem(imagesState,i,image)
                    Image(image.compressed,contentDescription = "ds",
                       // contentScale = ContentScale.FillBounds,
                        modifier = Modifier.
                        rotate(270.0f).fillParentMaxHeight()

                            .border(2.dp,Color.Transparent, RoundedCornerShape(5.dp)) .padding(5.dp)

                            .clickable {
                                imagesState.currentIndex = i
                            }
                    )
            }

        }
    }
    FolderNameDialog(imagesState)

}


fun runCMD(){
    val cache = "/Users/lawrence/Pictures/Law/mycache/two"
    val dir = "/Users/lawrence/Pictures/Law/test3"
    val cmd  = "exiftool -a -b -W /Users/lawrence/Pictures/Law/mycache/two/%f_%t%-c.%s -preview:PreviewImage ${dir}";
    println(cmd)
  Runtime.getRuntime().exec(cmd)
}

