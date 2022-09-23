package util

import java.io.File
import java.io.FileFilter

private class MyFileFilter : FileFilter {
    override fun accept(pathname: File): Boolean {
        return !pathname.isDirectory()
    }
}