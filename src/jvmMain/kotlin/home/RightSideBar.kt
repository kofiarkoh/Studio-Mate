package home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.AppButton

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RightSideBar(imagesState: ImageState) {
    Column(
        modifier = Modifier.padding(horizontal = 0.dp).fillMaxWidth()

    ) {


        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp, horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Photos Selected", fontWeight = FontWeight.Light,
                color = Color.White, fontSize = 20.sp
            )

            AppButton(onclick = {

            }, label = "Clear")

        }
        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 150.dp),
            modifier = Modifier.weight(.7f)
        ) {
            itemsIndexed(imagesState.selectedImages) { i, item ->
                Card(modifier = Modifier.padding(10.dp), elevation = 5.dp,
                    backgroundColor = Color.Transparent,
                    border = BorderStroke(1.dp,Color.White),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Row(
                        modifier = Modifier.width(150.dp).padding(0.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TextButton(
                            onClick = {
                                imagesState.setIndexBySelectedImage(item)
                            }
                        ) {
                            Text("${i + 1}. ${item.fileName}", color = Color.White)
                        }

                        IconButton(onClick = {
                            imagesState.removeImageFromSelections(i)
                        }) {
                            Icon(
                                Icons.Default.Close, "remove photo",
                                tint = androidx.compose.ui.graphics.Color.White
                            )
                        }
                    }
                }
            }

        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            AppButton(onclick = {
                imagesState.addImageToSelections(imagesState.currentIndex)
            }, label = "Select")
            AppButton(onclick = {
                // display folder name dialog
                imagesState.isDialogVisible = true
            }, label = "Save")
            AppButton(onclick = {
                // display folder name dialog
                imagesState.loadedImages.clear()
                imagesState.selectedImages.clear()
            }, label = "Reset")


        }


    }
}