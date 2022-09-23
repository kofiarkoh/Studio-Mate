package components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import home.ImageState

@Composable
fun FolderNameDialog(imageState: ImageState) {

    val scope = rememberCoroutineScope()


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth().fillMaxHeight()//.background(Color.Black.copy(.4f))

    ) {
        AnimatedVisibility(imageState.isDialogVisible) {
            Card(modifier = Modifier.size(300.dp, 200.dp)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(10.dp).fillMaxWidth().fillMaxHeight()
                ) {
                    Text(
                        text = "Enter folder name",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(vertical = 10.dp),
                        fontWeight = FontWeight.Light
                    )
                    TextField(
                        value = imageState.folderToSaveName,
                        onValueChange = { imageState.folderToSaveName = it },
                        placeholder = { Text("type here") },
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                        textStyle = TextStyle(fontSize = 20.sp),
                    )

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 20.dp).fillMaxWidth()
                    ) {
                        TextButton(onClick = {
                            imageState.isDialogVisible = false
                        }) {
                            Text("CANCEL", color = Color.Black)
                        }


                        AppButton(
                            "Submit",
                            onclick = {
                                imageState.saveSelections(scope)
                            },
                            borderColor = Color.Black
                        )

                    }

                }
            }


        }
    }
}