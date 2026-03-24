package com.example.appcatalog.screens.viewer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ScreenshotViewerScreen(
    screenshots: List<String>,
    startIndex: Int,
    onBackClick: () -> Unit
) {
    val safeStartIndex = startIndex.coerceIn(0, screenshots.lastIndex)
    val pagerState = rememberPagerState(
        initialPage = safeStartIndex,
        pageCount = { screenshots.size }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextButton(onClick = onBackClick) {
            Text(text = "← Назад")
        }

        Text(
            text = "Скриншот ${pagerState.currentPage + 1} из ${screenshots.size}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            ScreenshotViewerPlaceholder(
                type = screenshots[page],
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp)
            )
        }
    }
}

@Composable
private fun ScreenshotViewerPlaceholder(
    type: String,
    modifier: Modifier = Modifier
) {
    val color = when (type) {
        "purple" -> Color(0xFF7C4DFF)
        "teal" -> Color(0xFF26A69A)
        "orange" -> Color(0xFFFF7043)
        else -> Color.Gray
    }

    Column(
        modifier = modifier.background(
            color = color,
            shape = RoundedCornerShape(20.dp)
        )
    ) {}
}