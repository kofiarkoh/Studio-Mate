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
import androidx.compose.ui.draw.clip
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
import java.io.File
import java.nio.file.Files

@Composable
fun HomeLayout(){

    val imagesState = rememberImageState()

    Column(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {

        Row(modifier = Modifier.weight(.75f,true)
            .background(Color(31, 32, 39))
            .border(2.dp, Color.Transparent)){

            Column(
                modifier = Modifier.weight(.7f,true)
                  //  .background(Color.Black)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                FullImagePreview(imagesState)
            }
            Divider(modifier = Modifier.width(2.dp).fillMaxHeight(),
                color = Color(60, 60, 60), thickness = 5.dp)
            Column(
                modifier = Modifier.weight(.3f,true)
                    .fillMaxHeight()
            ) {
                RightSideBar(imagesState)
            }

        }

        LazyRow(
            modifier = Modifier

                .weight(.25f,true).fillMaxHeight().fillMaxWidth().
                    background(Color(60, 60, 60))
            ,
            contentPadding = PaddingValues(horizontal = 25.dp)

        ) {

            itemsIndexed(imagesState.loadedImages) {i,image->

                //ThumbnailRowItem(imagesState,i,image)
                    Image(image.compressed,contentDescription = "ds",
                       contentScale = ContentScale.Fit,
                        modifier = Modifier.
                        rotate(270.0f).fillParentMaxHeight()
                            .padding(10.dp)

                            .clip(RoundedCornerShape(10.dp))
                            .clickable {
                                imagesState.currentIndex = i
                            }
                    )
            }

        }
    }
    FolderNameDialog(imagesState)

}


fun runCMD(sourceDir:String): Process? {
    val cache = "/Users/lawrence/Pictures/Law/mycache"
    val dir = "/Users/lawrence/Pictures/Law/test3"
    Files.walk(File(cache).toPath())
        .filter { Files.isRegularFile(it) }
        .map { it.toFile() }
        .forEach { it.delete() }
    val cmd  = "exiftool -a -b -W /Users/lawrence/Pictures/Law/mycache/%f_%t%-c.%s -preview:PreviewImage ${sourceDir}";
    println(cmd)
    println("command run finish")
    return Runtime.getRuntime().exec(cmd)

}

