package home

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.ImageBitmap
import util.LoadedImages
import util.copySelectedImagesToDisk

class ImageState {
    var imagesDirectory by mutableStateOf("/Users/lawrence/Pictures/Law/test3")
    var folderToSaveName by mutableStateOf("Lawrence")
    val loadedImages = mutableStateListOf<LoadedImages>()
    val selectedImages = mutableStateListOf<LoadedImages>()
    var currentIndex by mutableStateOf(0)


    fun addImageToSelections(index:Int){
        if(loadedImages.isEmpty()){
            return
        }
        val selectedImage = loadedImages[index]
        //check if image is already selected
        val isSelected = selectedImages.contains(selectedImage)
        if(isSelected){

        }
        else{
            selectedImages.add(selectedImage)
        }

    }

    fun removeImageFromSelections(index: Int){
        selectedImages.removeAt(index)
    }

    fun saveSelections(){
        copySelectedImagesToDisk(selectedImages,imagesDirectory,folderToSaveName)
    }

    fun setIndexBySelectedImage(image: LoadedImages){
        currentIndex = loadedImages.indexOf(image)


    }
}

@Composable
fun rememberImageState() = remember { ImageState() }