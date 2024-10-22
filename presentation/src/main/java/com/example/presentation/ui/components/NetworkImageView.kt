package com.example.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import com.example.presentation.R

@Composable
fun NetworkImageView(url: String, description: String, modifier: Modifier = Modifier) {
    Image(
        painter = if (url != "") rememberAsyncImagePainter(model = url)
        else painterResource(id = R.drawable.baseline_arrow_back_24),
        contentDescription = description,
        modifier = modifier
    )
}