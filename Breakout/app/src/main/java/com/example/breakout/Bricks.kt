// Brick.kt
package com.example.breakout

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Brick(
    val position: Offset,
    val width: Dp = 30.dp,
    val height: Dp = 15.dp,
    val color: Color = Color.Green,
    var isVisible: Boolean = true
)
