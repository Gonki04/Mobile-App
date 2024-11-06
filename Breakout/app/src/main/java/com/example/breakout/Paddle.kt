// Paddle.kt
package com.example.breakout

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Paddle(
    var position: Offset = Offset(300f, 0f),
    val width: Dp = 150.dp,
    val height: Dp = 20.dp,
    val color: Color = Color.Blue
) {
    fun move(newX: Float) {
        position = Offset(newX, position.y)
    }

    fun contains(ballX: Float): Boolean {
        return ballX in position.x..(position.x + width.value)
    }
}
