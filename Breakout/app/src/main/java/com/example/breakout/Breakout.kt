// BreakoutGame.kt
package com.example.breakout

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun BreakoutGame() {
    val ball = remember { Ball(position = Offset(0f, 0f)) }
    val paddle = remember { Paddle(position = Offset(0f, 0f)) }
    val bricks = remember {
        mutableStateListOf<Brick>().apply {
            for (row in 0..4) {
                for (col in 0..6) {
                    add(Brick(position = Offset(col * 100f + 50f, row * 50f + 50f)))
                }
            }
        }
    }

    // Converter dimensões de Dp para pixels
    val density = LocalDensity.current
    val ballRadiusPx = with(density) { ball.radius.toPx() }
    val paddleWidthPx = with(density) { paddle.width.toPx() }
    val paddleHeightPx = with(density) { paddle.height.toPx() }
    val brickWidthPx = with(density) { bricks.firstOrNull()?.width?.toPx() ?: 0f }
    val brickHeightPx = with(density) { bricks.firstOrNull()?.height?.toPx() ?: 0f }

    LaunchedEffect(Unit) {
        while (true) {
            delay(16L)
            ball.updatePosition()

            // Colisão com as bordas laterais e superior
            if (ball.position.x <= 0 || ball.position.x >= paddleWidthPx) {
                ball.reverseX()
            }
            if (ball.position.y <= 0) {
                ball.reverseY()
            }

            // Colisão com o paddle
            if (ball.position.y >= paddle.position.y - ballRadiusPx &&
                ball.position.x in paddle.position.x..(paddle.position.x + paddleWidthPx)
            ) {
                ball.reverseY()
            }

            // Colisão com os bricks
            bricks.forEach { brick ->
                if (brick.isVisible && checkCollision(ball.position, brick, ballRadiusPx, brickWidthPx, brickHeightPx)) {
                    brick.isVisible = false
                    ball.reverseY()
                }
            }
        }
    }

    Canvas(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectHorizontalDragGestures { _, dragAmount ->
                val newX = (paddle.position.x + dragAmount).coerceIn(0f, paddleWidthPx)
                paddle.move(newX)
            }
        }
    ) {
        // Obter tamanho da tela com base no Canvas
        val screenWidth = size.width
        val screenHeight = size.height

        // Definir posição inicial da bola e do paddle
        if (ball.position == Offset(0f, 0f)) {
            ball.position = Offset(screenWidth / 2, screenHeight - 200f)
        }
        if (paddle.position == Offset(0f, 0f)) {
            paddle.position = Offset(screenWidth / 2 - paddleWidthPx / 2, screenHeight - 100f)
        }

        // Desenha a bola
        drawCircle(
            color = ball.color,
            radius = ballRadiusPx,
            center = ball.position
        )

        // Desenha o paddle
        drawRect(
            color = paddle.color,
            topLeft = paddle.position,
            size = androidx.compose.ui.geometry.Size(paddleWidthPx, paddleHeightPx)
        )

        // Desenha os bricks
        bricks.forEach { brick ->
            if (brick.isVisible) {
                drawRect(
                    color = brick.color,
                    topLeft = brick.position,
                    size = androidx.compose.ui.geometry.Size(brickWidthPx, brickHeightPx)
                )
            }
        }
    }
}

// Função para verificar a colisão entre a bola e o bloco
fun checkCollision(ballPos: Offset, brick: Brick, ballRadiusPx: Float, brickWidthPx: Float, brickHeightPx: Float): Boolean {
    return ballPos.x in brick.position.x..(brick.position.x + brickWidthPx) &&
            ballPos.y in brick.position.y..(brick.position.y + brickHeightPx)
}
