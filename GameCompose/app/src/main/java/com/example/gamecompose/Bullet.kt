package com.example.gamecompose

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect


class Bullet(context: Context, screenX: Int, screenY: Int) {
    var x: Int = 0
    var y: Int = 0
    var speed: Int = 30
    var gone: Boolean = false
    var width: Int
    var height: Int
    var bitmap: Bitmap
    var detectCollision: Rect
    private val screenX: Int = screenX

    init {
        val originalBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.bullet1)
        // Redimensionar o bitmap para 50% do tamanho original
        bitmap = Bitmap.createScaledBitmap(originalBitmap, originalBitmap.width / 6, originalBitmap.height / 6, false)
        width = bitmap.width
        height = bitmap.height
        detectCollision = Rect(x, y, bitmap.width, bitmap.height)
    }

    fun update(screenY: Int) {
        x += speed
        if (x >= screenX) {
            gone = true
        }
        detectCollision.left = x
        detectCollision.top = y
        detectCollision.right = x + width
        detectCollision.bottom = y + height
    }
}