package com.example.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculator.ui.theme.Pink40

@Composable
fun CalculatorApp(modifier: Modifier = Modifier) {
    var displayText by remember { mutableStateOf("0") }
    var firstOperand by remember { mutableStateOf("") }
    var secondOperand by remember { mutableStateOf("") }
    var operator by remember { mutableStateOf<Char?>(null) }

    Column(modifier = modifier) {
        Text(text = displayText)
        Row {
            Button(onClick = { updateDisplay("7", displayText, { displayText = it })},
                colors = ButtonDefaults.run { buttonColors(
                Pink40) })
            {
                Text(text = "7")
            }
            Button(onClick = { updateDisplay("8", displayText, { displayText = it }) })
            {
                Text(text = "8")
            }
            Button(onClick = { updateDisplay("9", displayText, { displayText = it }) })
            {
                Text(text = "9")
            }
            Button(onClick = {
                operator = 'x'
                firstOperand = displayText
                displayText = "0"
            }) {
                Text(text = "x")
            }
        }
        Row {
            Button(onClick = { updateDisplay("4", displayText, { displayText = it }) })
            {
                Text(text = "4")
            }
            Button(onClick = { updateDisplay("5", displayText, { displayText = it }) })
            {
                Text(text = "5")
            }
            Button(onClick = { updateDisplay("6", displayText, { displayText = it }) })
            {
                Text(text = "6")
            }
            Button(onClick = {
                operator = '-'
                firstOperand = displayText
                displayText = "0"
            }) {
                Text(text = "-")
            }
        }
        Row {
            Button(onClick = { updateDisplay("1", displayText, { displayText = it }) })
            {
                Text(text = "1")
            }
            Button(onClick = { updateDisplay("2", displayText, { displayText = it }) })
            {
                Text(text = "2")
            }
            Button(onClick = { updateDisplay("3", displayText, { displayText = it }) })
            {
                Text(text = "3")
            }
            Button(onClick = {
                operator = '+'
                firstOperand = displayText
                displayText = "0"
            }) {
                Text(text = "+")
            }
        }
        Row {
            Button(onClick = { }) {
                Text(text = "+/-")
            }
            Button(onClick = { updateDisplay("0", displayText, { displayText = it }) })
            {
                Text(text = "0")
            }
            Button(onClick = { updateDisplay(".", displayText, { displayText = it }) })
            {
                Text(text = ",")
            }
            Button(onClick = {
                secondOperand = displayText
                displayText = calculateResult(firstOperand, secondOperand, operator)
            }) {
                Text(text = "=")
            }
        }
    }
}

fun updateDisplay(value: String, currentDisplay: String, update: (String) -> Unit)
{
    if (currentDisplay == "0") {
        update(value)
    } else {
        update(currentDisplay + value)
    }
}

fun calculateResult(first: String, second: String, operator: Char?): String
{
    val firstNum = first.toDoubleOrNull() ?: 0.0
    val secondNum = second.toDoubleOrNull() ?: 0.0

    return when (operator) {
        '+' -> (firstNum + secondNum).toString()
        '-' -> (firstNum - secondNum).toString()
        'x' -> (firstNum * secondNum).toString()
        else -> "0"
    }
}

@Preview(showBackground = true)
@Composable
fun CalcScreenPreview()
{
    CalculatorApp()
}
