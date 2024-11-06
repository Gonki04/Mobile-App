// Ball.kt
package com.example.breakout

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Ball(
    var position: Offset = Offset(400f, 800f),
    var speed: Offset = Offset(5f, -5f),
    val radius: Dp = 15.dp,
    val color: Color = Color.Red
) {
    fun updatePosition() {
        position += speed
    }

    fun reverseX() {
        speed = Offset(-speed.x, speed.y)
    }

    fun reverseY() {
        speed = Offset(speed.x, -speed.y)
    }

    fun resetPosition(newPosition: Offset, newSpeed: Offset) {
        position = newPosition
        speed = newSpeed
    }
}
