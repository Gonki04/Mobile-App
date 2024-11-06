package com.example.gamecompose

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect

class Player {
    var x = 0
    var y = 0
    var speed = 0
    var maxX = 0
    var maxY = 0
    var minX = 0
    var minY = 0

    var bitmap: Bitmap
    var boosting = false

    private val GRAVITY = -10
    private val MAX_SPEED = 20
    private val MIN_SPEED = 1

    var detectColision: Rect

    var health: Int = 5

    constructor(context: Context, width: Int, height: Int) {
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.player)

        maxX = width
        maxY = height - bitmap.height
        minX = 0
        minY = 0

        x = 75
        y = 50
        speed = 1

        detectColision = Rect(x, y, bitmap.width, bitmap.height)
    }

    fun update() {
        if (boosting) speed += 2
        else speed -= 5
        if (speed > MAX_SPEED) speed = MAX_SPEED
        if (speed < MIN_SPEED) speed = MIN_SPEED

        y -= speed + GRAVITY
        if (y < minY) y = minY
        if (y > maxY) y = maxY

        detectColision.left = x
        detectColision.top = y
        detectColision.right = x + bitmap.width
        detectColision.bottom = y + bitmap.height

    }
}