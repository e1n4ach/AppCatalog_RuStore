package com.example.appcatalog.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.appcatalog.model.AppItem
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.background

@Composable
fun DetailsScreen(
    app: AppItem,
    onBackClick: () -> Unit,
    onInstallClick: () -> Unit,
    onScreenshotClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        TextButton(onClick = onBackClick) {
            Text(text = "← Назад")
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = app.iconResId),
                contentDescription = app.name,
                modifier = Modifier
                    .size(72.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            )

            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = app.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Разработчик: ${app.developer}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Категория: ${app.category}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Возрастной рейтинг: ${app.ageRating}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Скриншоты",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(app.screenshots) { index, screenshotType ->
                Card(
                    modifier = Modifier.clickable { onScreenshotClick(index) }
                ) {
                    ScreenshotPlaceholder(
                        type = screenshotType,
                        modifier = Modifier.size(width = 180.dp, height = 320.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Описание",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = app.fullDescription,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onInstallClick) {
            Text(text = "Установить")
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun ScreenshotPlaceholder(
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
        modifier = modifier
            .background(color = color, shape = RoundedCornerShape(16.dp))
    ) {}
}