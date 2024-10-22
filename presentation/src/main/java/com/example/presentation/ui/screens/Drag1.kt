package com.example.presentation.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.presentation.theme.BobsBurgerTheme
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun RubberBandDraggableScaleBox() {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    val minOffset = Offset(0f, 0f)
    val maxOffset = Offset(500f, 500f)
    val scale = remember { Animatable(1f) }
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        // Animate scale back to normal when the drag ends
                        scope.launch {
                            scale.animateTo(1f, animationSpec = tween(30))
                        }
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()

                        val newOffsetX = offsetX + dragAmount.x
                        val newOffsetY = offsetY + dragAmount.y

                        offsetX = newOffsetX.coerceIn(minOffset.x, maxOffset.x)
                        offsetY = newOffsetY.coerceIn(minOffset.y, maxOffset.y)

                        // Apply rubber band effect to scale beyond the bounds
                        val scaleFactorX = when {
                            newOffsetX < minOffset.x -> 1 + (minOffset.x - newOffsetX) / 300f
                            newOffsetX > maxOffset.x -> 1 + (newOffsetX - maxOffset.x) / 300f
                            else -> 1f
                        }

                        val scaleFactorY = when {
                            newOffsetY < minOffset.y -> 1 + (minOffset.y - newOffsetY) / 300f
                            newOffsetY > maxOffset.y -> 1 + (newOffsetY - maxOffset.y) / 300f
                            else -> 1f
                        }

                        scope.launch {
//                            scale.snapTo(maxOf(scaleFactorX, scaleFactorY))
                            scale.animateTo(maxOf(scaleFactorX, scaleFactorY))
                        }
                    }
                )
            }.offset {
                IntOffset(
                    offsetX.roundToInt(),
                    offsetY.roundToInt()
                )
            }.scale(scale.value)
    ) {
        Box(
            modifier = Modifier
                .background(Color.Red)
                .size(100.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRubberBandDraggableScaleBox(){
    BobsBurgerTheme {
        RubberBandDraggableScaleBox()
    }
}
