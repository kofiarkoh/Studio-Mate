package util

import java.io.File
import java.io.FileFilter

class DsStoreFileFilter : FileFilter {
    override fun accept(pathname: File): Boolean {
        return !pathname.endsWith(".DS_Store")
    }
}