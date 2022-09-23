// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import home.HomeLayout
import java.awt.Dimension


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Studio Mate"

    ) {
        setMinSize()
        HomeLayout()


    }


}


fun FrameWindowScope.setMinSize() {
    window.minimumSize = Dimension(1200, 800)
}
