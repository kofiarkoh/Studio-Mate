import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import net.coobird.thumbnailator.Thumbnailator
import java.io.File


class ImageConverter(

) {
    val compressedfolder = File("/Users/lawrence/Pictures/Law/mycache")

    companion object {
        fun getThumbnail(file: File): ImageBitmap {

            return Thumbnailator.createThumbnail(file, 300, 500).toComposeImageBitmap()
        }
    }


}