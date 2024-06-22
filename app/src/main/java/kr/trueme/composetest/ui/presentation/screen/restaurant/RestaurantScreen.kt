package kr.trueme.composetest.ui.presentation.screen.restaurant

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import kr.trueme.composetest.ui.presentation.compnent.StandardTopBar

@Composable
fun RestaurantScreen(
    navController: NavController,
    url: String
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardTopBar(
            onTabLeft = {
                navController.navigateUp()
            }
        )
        WebViewPage(url)
    }



}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewPage(url: String) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                settings.allowContentAccess = true
                loadUrl(url)
            }
        }
    )
}