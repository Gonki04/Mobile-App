package com.example.gamecompose

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import java.util.Random

class Enemy {
    var x = 0
    var y = 0
    var speed = 0
    var maxX = 0
    var maxY = 0
    var minX = 0
    var minY = 0

    var bitmap: Bitmap

    var boosting = false
    val generator = Random()

    private val GRAVITY = -10
    private val MAX_SPEED = 20
    private val MIN_SPEED = 1
    var detectColision: Rect


    constructor(context: Context, width: Int, height: Int) {
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.enemy)

        maxX = width
        maxY = height - bitmap.height
        minX = 0
        minY = 0

        x = maxX

        y = generator.nextInt(maxY)

        speed = generator.nextInt(6) + 10

        detectColision = Rect(x, y, bitmap.width, bitmap.height)
    }

    fun update(playerSpeed: Int) {
        x -= playerSpeed
        x -= speed

        val generator = Random()

        if (x < minY - bitmap.width) {
            x = maxX
            y = generator.nextInt(maxY)
            speed = generator.nextInt(6) + 10
        }

        detectColision.left = x
        detectColision.top = y
        detectColision.right = x + bitmap.width
        detectColision.bottom = y + bitmap.height
    }
}