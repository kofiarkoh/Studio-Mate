package home

import androidx.compose.runtime.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import util.LoadedImages
import util.copySelectedImagesToDisk

class ImageState {
    val cacheFolder = "/Users/lawrence/Pictures/Law/mycache"
    var imagesDirectory by mutableStateOf("/Users/lawrence/Pictures/Law/test3")
    var folderToSaveName by mutableStateOf("Lawrence")
    val loadedImages = mutableStateListOf<LoadedImages>()
    val selectedImages = mutableStateListOf<LoadedImages>()
    var currentIndex by mutableStateOf(0)
    var isDialogVisible by mutableStateOf(false)
    var isNotificationVisible = remember { mutableStateOf(false) }


    fun addImageToSelections(index: Int) {
        if (loadedImages.isEmpty()) {
            return
        }
        val selectedImage = loadedImages[index]
        //check if image is already selected
        val isSelected = selectedImages.contains(selectedImage)
        if (isSelected) {

        } else {
            selectedImages.add(selectedImage)
        }

    }

    fun removeImageFromSelections(index: Int) {
        selectedImages.removeAt(index)
    }

    fun saveSelections(scope: CoroutineScope) {

        // hide dialog first
        isDialogVisible = false
        scope.launch(Dispatchers.IO) {
            copySelectedImagesToDisk(selectedImages, imagesDirectory, folderToSaveName)

            selectedImages.clear()
            folderToSaveName = ""
        }
    }

    fun setIndexBySelectedImage(image: LoadedImages) {
        currentIndex = loadedImages.indexOf(image)

    }

    fun onCopySucess() {

    }
}

@Composable
fun rememberImageState() = remember { ImageState() }