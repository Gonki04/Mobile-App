package com.example.gamecompose

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.TextView
import androidx.core.text.color

class GameView : SurfaceView, Runnable {

    var onGameOver: () -> Unit = {}
    var playing = false
    var gameThread: Thread? = null
    lateinit var surfaceHolder: SurfaceHolder
    lateinit var canvas: Canvas
    lateinit var player: Player
    var enemies = arrayListOf<Enemy>()
    lateinit var boom: Boom

    lateinit var paint: Paint
    var stars = arrayListOf<Star>()
    var bullets = arrayListOf<Bullet>()
    var touchEvent: MotionEvent? = null

    private var boostRect: RectF? = null
    private var shootRect: RectF? = null
    var score = 0

    private fun init(context: Context, width: Int, height: Int) {

        surfaceHolder = holder
        paint = Paint()

        for (i in 0..100) {
            stars.add(Star(width, height))
        }
        for (i in 0..2) {
            enemies.add(Enemy(context, width, height))
        }
        player = Player(context, width, height)
        boom = Boom(context, width, height)
    }

    constructor(context: Context?, width: Int, height: Int) : super(context) {
        init(context!!, width, height)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context!!, 0, 0)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context!!, 0, 0)
    }

    fun resume() {
        playing = true
        gameThread = Thread(this)
        gameThread?.start()
    }

    fun pause() {
        playing = false
        gameThread?.join()
    }

    override fun run() {
        while (playing) {
            update()
            draw()
            control()
        }
    }

    fun update() {
        boom.x = -300
        boom.y = -300

        for (s in stars) {
            s.update(player.speed)
        }
        for (e in enemies) {
            e.update(player.speed)
            if (Rect.intersects(player.detectColision, e.detectColision)) {
                boom.x = e.x
                boom.y = e.y
                e.x = -300
                player.health--
                if (player.health == 0) {
                    onGameOver()
                }
            }
        }
        player.update()

        // Atualizar a posição dos projéteis
        val bulletsToRemove = mutableListOf<Bullet>()
        for (bullet in bullets) {
            bullet.update(width)
            if (bullet.gone) {
                bulletsToRemove.add(bullet)
            }
            // Verificar colisão com inimigos
            for (enemy in enemies) {
                if (Rect.intersects(bullet.detectCollision, enemy.detectColision)) {
                    score++
                    boom.x = enemy.x
                    boom.y = enemy.y
                    enemy.x = -300
                    bullet.gone = true
                    bulletsToRemove.add(bullet)
                    break // Sair do loop interno após a colisão
                }
            }
        }
        bullets.removeAll(bulletsToRemove)

    }

    fun draw() {
        if (surfaceHolder.surface.isValid) {
            canvas = surfaceHolder.lockCanvas()

            // design code here
            canvas.drawColor(Color.BLACK)
            paint.color = Color.YELLOW
            for (star in stars) {
                paint.strokeWidth = star.starWidth.toFloat()
                canvas.drawPoint(star.x.toFloat(), star.y.toFloat(), paint)
            }
            canvas.drawBitmap(boom.bitmap, boom.x.toFloat(), boom.y.toFloat(), paint)
            canvas.drawBitmap(player.bitmap, player.x.toFloat(), player.y.toFloat(), paint)

            for (e in enemies) {
                canvas.drawBitmap(e.bitmap, e.x.toFloat(), e.y.toFloat(), paint)
            }
            for (bullet in bullets) {
                canvas.drawBitmap(bullet.bitmap, bullet.x.toFloat(), bullet.y.toFloat(), paint)
            }
            paint.color = Color.WHITE
            paint.textSize = 50f
            canvas.drawText("Score: $score", width- 240f, 100f, paint)
            canvas.drawText("Health: ${player.health}", width-240f, 200f, paint)

            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }

    fun control() {
        Thread.sleep(17)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // Definir a área de boost (metade esquerda da tela)
        boostRect = RectF(0f, 0f, w / 2f, h.toFloat())
        // Definir a área de disparo (metade direita da tela)
        shootRect = RectF(w / 2f, 0f, w.toFloat(), h.toFloat())
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        touchEvent = event
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (boostRect!!.contains(event.x, event.y)) {
                    player.boosting = true
                } else if (shootRect!!.contains(event.x, event.y)) {
                    val bullet = Bullet(context, width, height)
                    bullet.x = player.x + player.bitmap.width
                    bullet.y = player.y + player.bitmap.height / 2
                    bullets.add(bullet)
                }
            }
            MotionEvent.ACTION_UP -> {
                player.boosting = false
            }
        }
        return true
    }

    /*fun gameOver() {
        playing = false
        gameOver = true
        onGameOver() // Notifica o GameScreenView que o jogo terminou
    }*/

    fun resetGame() {
        playing = true
        gameThread = Thread(this)
        gameThread?.start()
    }
}