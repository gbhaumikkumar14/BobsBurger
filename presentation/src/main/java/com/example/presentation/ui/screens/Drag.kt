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
fun RubberBandDraggableOffsetBox() {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    val minOffset = Offset(-300f, -300f)
    val maxOffset = Offset(0f, 0f)
    val animOffsetX = remember { Animatable(0f) }
    val animOffsetY = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(offsetX, offsetY) {
        animOffsetX.snapTo(offsetX)
        animOffsetY.snapTo(offsetY)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        // Animate back to bounds when the drag ends
                        scope.launch {
                            animOffsetX.animateTo(
                                targetValue = offsetX.coerceIn(minOffset.x, maxOffset.x),
                                animationSpec = tween(300)
                            )
                            animOffsetY.animateTo(
                                targetValue = offsetY.coerceIn(minOffset.y, maxOffset.y),
                                animationSpec = tween(300)
                            )
                        }
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()

                        val newOffsetX = offsetX + dragAmount.x
                        val newOffsetY = offsetY + dragAmount.y

                        // Apply rubber band effect beyond the bounds
                        offsetX = when {
                            newOffsetX < minOffset.x -> minOffset.x + (newOffsetX - minOffset.x) / 3
                            newOffsetX > maxOffset.x -> maxOffset.x + (newOffsetX - maxOffset.x) / 3
                            else -> newOffsetX
                        }

                        offsetY = when {
                            newOffsetY < minOffset.y -> minOffset.y + (newOffsetY - minOffset.y) / 3
                            newOffsetY > maxOffset.y -> maxOffset.y + (newOffsetY - maxOffset.y) / 3
                            else -> newOffsetY
                        }
                    }
                )
            }
    ) {
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(
                        animOffsetX.value.roundToInt(),
                        animOffsetY.value.roundToInt()
                    )
                }
                .background(Color.Red)
                .size(100.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRubberBandDraggableOffsetBox(){
    BobsBurgerTheme {
        RubberBandDraggableOffsetBox()
    }
}
