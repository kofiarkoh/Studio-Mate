package home

import androidx.compose.runtime.*
import components.Notification
import kotlinx.coroutines.*
import util.LoadedImages
import util.copySelectedImagesToDisk

class ImageState {
    val cacheFolder = "${ System.getProperty("user.home","/")}/Pictures/StudioMate/cache/" //"/Users/lawrence/Pictures/Law/mycache"
    var imagesDirectory by mutableStateOf("/Users/lawrence/Pictures/Law/test3")
    var folderToSaveName by mutableStateOf("")
    val loadedImages = mutableStateListOf<LoadedImages>()
    val selectedImages = mutableStateListOf<LoadedImages>()
    var currentIndex by mutableStateOf(0)
    var isDialogVisible by mutableStateOf(false)
    var isNotificationVisible = mutableStateOf(false)
    var notification by mutableStateOf(Notification())
    var totalImagesToLoad by mutableStateOf(0)


    suspend fun addImageToSelections(index: Int) {
        if (loadedImages.isEmpty()) {
            return
        }
        val selectedImage = loadedImages[index]
        //check if image is already selected
        val isSelected = selectedImages.contains(selectedImage)
        if (isSelected) {
            sendNotification("The current photo has been already selected.")
        } else {
            selectedImages.add(selectedImage)
        }

    }

    fun removeImageFromSelections(index: Int) {
        selectedImages.removeAt(index)
    }

    fun saveSelections(scope: CoroutineScope) {

        // validate folder name
        val folderNameRegex = Regex("\\W")
        if (folderNameRegex.matches(folderToSaveName)) {
            scope.launch {
                sendNotification("Folder name can only be letters and numbers.")
            }
            return
        }
        if (folderToSaveName.length < 2) {
            scope.launch {
                sendNotification("Folder name must be at least 2 characters.")
            }
            return
        }

        // hide dialog first
        isDialogVisible = false
        scope.launch(Dispatchers.IO) {
            try {
                copySelectedImagesToDisk(selectedImages, imagesDirectory, folderToSaveName)

                selectedImages.clear()
                folderToSaveName = ""
                sendNotification("Photos has been saved successfully")
            } catch (e: Exception) {
                sendNotification(
                    e.message ?: "Photos could not be saved to disk."
                )
            }

        }
    }

    fun setIndexBySelectedImage(image: LoadedImages) {
        currentIndex = loadedImages.indexOf(image)

    }

    fun onCopySucess() {

    }

    suspend fun sendNotification(msg: String) {
        /* set and hide notification after 5 seconds
        * */
        coroutineScope {
            launch(Dispatchers.IO) {

                notification = Notification(message = msg)
                isNotificationVisible.value = true
                delay(5000L)
                isNotificationVisible.value = false
            }
        }

    }
}

@Composable
fun rememberImageState() = remember { ImageState() }