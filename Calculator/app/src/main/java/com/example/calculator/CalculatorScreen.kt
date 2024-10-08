package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.Components.CalcButton
import com.example.calculator.ui.theme.Pink40

@Composable
fun CalculatorApp(modifier: Modifier = Modifier) {

    var displayText by remember { mutableStateOf("0") }
    var operator by remember { mutableStateOf("") }
    var operand by remember { mutableStateOf(0.0) }

    fun setDisplay(value: Double) {
        if (value % 1 == 0.0) {
            displayText = value.toInt().toString()
        } else {
            displayText = value.toString()
        }
    }

    var userIsInTheMiddleOfIntroducing by remember { mutableStateOf(true) }

    val onNumPressed: (String) -> Unit = { num ->
        if (userIsInTheMiddleOfIntroducing) {
            if (displayText == "0") {
                if (num == ".") {
                    displayText = "0."
                } else {
                    displayText = num
                }
            } else {
                if (num == ".") { //Verifica se a tecla pressionada é a do ponto
                    if (!displayText.contains('.')) { //Verifica se já existe um ponte
                        displayText += num
                    }
                } else {
                    displayText += num
                }
            }
        } else {
            displayText = num
        }
        userIsInTheMiddleOfIntroducing = true
    }

    val onACPressed: (String) -> Unit = {
        displayText = "0"
    }

    val onOpPressed: (String) -> Unit = { op ->
        if (operator.isNotEmpty()) {
            when (operator) {
                "+" -> operand += displayText.toDouble()
                "-" -> operand -= displayText.toDouble()
                "x" -> operand *= displayText.toDouble()
                "/" -> operand /= displayText.toDouble()
                "=" -> operator = ""
            }
            setDisplay(operand)
        }
        operand = displayText.toDouble()
        operator = op
        userIsInTheMiddleOfIntroducing = false
    }

    Column(modifier = modifier.background(Color.Gray))
    {
        Text(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .background(Color.Gray)
                .wrapContentHeight(align = Alignment.CenterVertically),
            text = displayText,
            fontSize = 50.sp,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Right
        )
        Row(
            modifier = Modifier
                .weight(0.5f)
                .aspectRatio(4f)
        ) {
            CalcButton(modifier = Modifier.weight(1f), label = "AC", onClick = onACPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "+/-", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "%", onClick = onNumPressed)
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "/",
                isOperation = true,
                onClick = onOpPressed
            )
        }
        Row(
            modifier = Modifier
                .weight(0.5f)
                .aspectRatio(4f)
        ) {
            CalcButton(modifier = Modifier.weight(1f), label = "7", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "8", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "9", onClick = onNumPressed)
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "x",
                isOperation = true,
                onClick = onOpPressed
            )
        }
        Row(
            modifier = Modifier
                .weight(0.5f)
                .aspectRatio(4f)
        ) {
            CalcButton(modifier = Modifier.weight(1f), label = "4", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "5", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "6", onClick = onNumPressed)
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "-",
                isOperation = true,
                onClick = onOpPressed
            )
        }
        Row(
            modifier = Modifier
                .weight(0.5f)
                .aspectRatio(4f)
        ) {
            CalcButton(modifier = Modifier.weight(1f), label = "1", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "2", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "3", onClick = onNumPressed)
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "+",
                isOperation = true,
                onClick = onOpPressed
            )
        }
        Row(
            modifier = Modifier
                .weight(0.5f)
                .aspectRatio(4f)
        ) {
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "",
                isOperation = true,
                onClick = {})
            CalcButton(modifier = Modifier.weight(1f), label = "0", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = ".", onClick = onNumPressed)
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "=",
                isOperation = true,
                onClick = onOpPressed
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalcScreenPreview() {
    CalculatorApp()
}