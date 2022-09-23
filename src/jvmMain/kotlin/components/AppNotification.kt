package components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import home.ImageState

@Composable
fun AppNotification(imageState: ImageState) {


    Column(
        modifier = Modifier.fillMaxHeight().fillMaxWidth(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom
    ) {

        /*Button(onClick = {isNotificationVisible.value = true}){
            Text("show")
        }
        Button(onClick = {isNotificationVisible.value = false}){
            Text("hide")
        }*/
        AnimatedVisibility(visible = imageState.isNotificationVisible.value) {


            Card(modifier = Modifier.width(350.dp).padding(20.dp)) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Image(
                        painter = painterResource("infoicon.png"),
                        contentDescription = "notification",
                        modifier = Modifier.padding(end = 10.dp)
                    )
                    Text(
                        imageState.notification.message,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }

    }
}

data class Notification(
    var status: String = "info",
    var message: String = ""
)