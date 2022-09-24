package components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AppButton(
    label: String,
    onclick: () -> Unit,
    borderColor: Color = Color(255, 255, 255)
) {

    OutlinedButton(
        onClick = onclick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        border = BorderStroke(1.dp, borderColor),
        shape = RoundedCornerShape(40.dp),
        // modifier = Modifier.height(30.dp)

    ) {

        Text(label, color = borderColor)
    }


}

@Composable
fun SelectedPhotoButton(label: String, onclick: () -> Unit) {

    OutlinedButton(
        onClick = onclick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        border = BorderStroke(1.dp, Color(255, 255, 255)),
        shape = RoundedCornerShape(40.dp),
        // modifier = Modifier.height(30.dp)

    ) {

        Text(label, color = Color.White)
    }


}