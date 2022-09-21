package components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import home.ImageState

@Composable
fun FolderNameDialog(imageState: ImageState){


    AnimatedVisibility(imageState.isDialogVisible) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().fillMaxHeight().background(Color.Black.copy(.4f))

        ) {
            Card(modifier= Modifier.size(300.dp,200.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(10.dp).fillMaxWidth().fillMaxHeight()
                ) {
                    Text(text ="Enter folder name",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(vertical = 10.dp),
                        fontWeight = FontWeight.Black
                    )
                    TextField(value = imageState.folderToSaveName, onValueChange = {imageState.folderToSaveName = it})

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 20.dp).fillMaxWidth()
                    ) {
                        TextButton(onClick = {
                            imageState.isDialogVisible = false
                        }){
                            Text("CANCEL")
                        }
                        Button(onClick = {
                            imageState.saveSelections()
                        }){
                            Text("SUBMIT")
                        }
                    }

                }
            }


        }
    }
}