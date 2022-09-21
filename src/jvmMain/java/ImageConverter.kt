import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import net.coobird.thumbnailator.Thumbnailator
import net.coobird.thumbnailator.Thumbnails
import net.coobird.thumbnailator.makers.ThumbnailMaker
import util.loadImageBitmap
import java.awt.AlphaComposite
import java.awt.Dimension
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO


class ImageConverter(

) {
    val compressedfolder = File("/Users/lawrence/Pictures/Law/mycache")

    companion object{
        fun getThumbnail(file: File): ImageBitmap{

            return  Thumbnailator.createThumbnail(file,300,500).toComposeImageBitmap()
        }
    }


}