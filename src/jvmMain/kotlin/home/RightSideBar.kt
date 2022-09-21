package home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RightSideBar(imagesState: ImageState) {
    Column(
        modifier = Modifier.padding(horizontal = 0.dp).fillMaxWidth()

    ) {

        Row(
            modifier = Modifier.background(Color(200, 200, 200)).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                imagesState.addImageToSelections(imagesState.currentIndex)
            },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(35, 12, 187))

            ) {
                Text("SELECT",color= androidx.compose.ui.graphics.Color.White)
            }

            Button(onClick = {
                // display folder name dialog
                imagesState.isDialogVisible = true
            },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(8, 134, 53))

            ) {
                Text("SAVE",color= androidx.compose.ui.graphics.Color.White)
            }
            Button(onClick = {
                // display folder name dialog
                imagesState.loadedImages.clear()
                imagesState.selectedImages.clear()
            },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(134, 24, 53))

            ) {
                Text("RESET",color= androidx.compose.ui.graphics.Color.White)
            }
        }

        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp, horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text("SELECTED PHOTOS", fontWeight = FontWeight.Black ,
                color = Color(50,50,50))

            TextButton(onClick = {

            },
                colors = ButtonDefaults.buttonColors(backgroundColor = androidx.compose.ui.graphics.Color.LightGray)
            ) {
                Text("CLEAR SELECTIONS", color = androidx.compose.ui.graphics.Color.Red.copy(.7f))
            }
        }
        LazyVerticalGrid(cells = GridCells.Adaptive(minSize = 180.dp)) {
            itemsIndexed(imagesState.selectedImages) { i, item ->
                Card(modifier = Modifier.padding(10.dp), elevation = 5.dp) {
                    Row(
                        modifier = Modifier.width(150.dp).padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TextButton(
                            onClick = {
                                imagesState.setIndexBySelectedImage(item)
                            }
                        ) {
                            Text("${i + 1}. ${item.fileName}")
                        }

                        IconButton(onClick = {
                            imagesState.removeImageFromSelections(i)
                        }) {
                            Icon(
                                Icons.Default.Delete, "remove photo",
                                tint = androidx.compose.ui.graphics.Color.Red
                            )
                        }
                    }
                }
            }

        }


    }
}