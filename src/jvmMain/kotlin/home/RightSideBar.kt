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
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RightSideBar(imagesState: ImageState) {
    Column(
        modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth()

    ) {

        Row(modifier = Modifier.background(androidx.compose.ui.graphics.Color.Gray).fillMaxWidth()) {
            Button(onClick = {
                imagesState.addImageToSelections(imagesState.currentIndex)
            }) {
                Text("SELECT")
            }

            Button(onClick = {
                // display folder name dialog
                imagesState.isDialogVisible = true
            }) {
                Text("SAVE")
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