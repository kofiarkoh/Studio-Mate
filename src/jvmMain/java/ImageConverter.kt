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
    private val files: Array<File>,
    private val resizedWidth: Int,
    private val resizedHeight: Int,
    private val fileType: String
) {
    val compressedfolder = File("/Users/lawrence/Pictures/Law/mycache")


    fun convertImages(): String {
        return try {
            for (i in files.indices) {
                println("converting ${files[i]}")

                val file = files[i]
                if(file.absolutePath.endsWith("DS_Store")){
                    continue
                }
                val filename = file.name.substring(0, file.name.lastIndexOf(".")) + "." + fileType
                val newFileName =  file.absolutePath.replace(file.name, "resized_$filename")
                val originalImage = ImageIO.read(file)
                var type = originalImage.type
                if (type == 0) {
                    type = BufferedImage.TYPE_INT_ARGB
                }
                val resizedImage = resizedImage(originalImage, type)
                ImageIO.write(resizedImage, fileType, File(newFileName))
            }
            "File Successfully created!"
        } catch (e: IOException) {
            "File conversion failed!" + e.message
        }
    }

    fun convertImage(file:File):ImageBitmap{
        val filename = file.name.substring(0, file.name.lastIndexOf(".")) + "." + fileType
        val newFileName = "/Users/lawrence/Pictures/Law/mycache/${file.nameWithoutExtension}.${fileType}" //  file.absolutePath.replace(file.name, "resized_$filename")
        val originalImage = ImageIO.read(file)
        var type = originalImage.type
        if (type == 0) {
            type = BufferedImage.TYPE_INT_ARGB
        }
        val resizedImage = resizedImage(originalImage, type)
        ImageIO.write(resizedImage, fileType, File(newFileName))
        //return bitmap
        return loadImageBitmap(File(newFileName))
    }
    private fun resizedImage(originalImage: BufferedImage, type: Int): BufferedImage {
        val resizedImage = BufferedImage(resizedWidth, resizedHeight, type)
        val g = resizedImage.createGraphics()

        g.setRenderingHint(
            RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BILINEAR
        )
        g.setRenderingHint(
            RenderingHints.KEY_RENDERING,
            RenderingHints.VALUE_RENDER_QUALITY
        )
        g.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        )

        g.drawImage(originalImage, 0, 0, resizedWidth, resizedHeight, null)
        g.dispose()
        g.composite = AlphaComposite.Src
        return resizedImage
    }

    fun getThumbnail(file: File): ImageBitmap{




        return  Thumbnailator.createThumbnail(file,300,500).toComposeImageBitmap()
    }

}