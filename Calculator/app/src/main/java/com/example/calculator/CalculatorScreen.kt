package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.calculator.ui.theme.Components.CalcButton
import com.example.calculator.ui.theme.Pink40

@Composable
fun CalculatorApp(modifier: Modifier = Modifier) {

    var displayText by remember { mutableStateOf("0") }
    val onNumPressed: (String) -> Unit = { num ->
        if (displayText == "0") {
            displayText = num
        } else {
            displayText += num
        }
    }
    val onACPressed: (String) -> Unit = {
        displayText = "0"
    }
    Column(modifier = modifier.background(Color.Gray))
    {
        Text(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .background(Color.Gray),
            text = displayText,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Right)
        Row(
            modifier = Modifier
                .weight(0.5f)
                .aspectRatio(4f)
        ) {
            CalcButton(modifier = Modifier.weight(1f), label = "AC", onClick = onACPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "+/-", onClick =  onNumPressed )
            CalcButton(modifier = Modifier.weight(1f), label = "%", onClick =  onNumPressed )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "/",
                isOperation = true,
                onClick = {})
        }
        Row(
            modifier = Modifier
                .weight(0.5f)
                .aspectRatio(4f)
        ) {
            CalcButton(modifier = Modifier.weight(1f), label = "7", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "8", onClick =  onNumPressed )
            CalcButton(modifier = Modifier.weight(1f), label = "9", onClick =  onNumPressed )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "x",
                isOperation = true,
                onClick = {})
        }
        Row(
            modifier = Modifier
                .weight(0.5f)
                .aspectRatio(4f)
        ) {
            CalcButton(modifier = Modifier.weight(1f), label = "4", onClick =  onNumPressed )
            CalcButton(modifier = Modifier.weight(1f), label = "5", onClick =  onNumPressed )
            CalcButton(modifier = Modifier.weight(1f), label = "6", onClick =  onNumPressed )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "-",
                isOperation = true,
                onClick = {})
        }
        Row(
            modifier = Modifier
                .weight(0.5f)
                .aspectRatio(4f)
        ) {
            CalcButton(modifier = Modifier.weight(1f), label = "1", onClick =  onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "2", onClick =  onNumPressed )
            CalcButton(modifier = Modifier.weight(1f), label = "3", onClick =  onNumPressed )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "+",
                isOperation = true,
                onClick = {})
        }
        Row(
            modifier = Modifier
                .weight(0.5f)
                .aspectRatio(4f)
        ) {
            CalcButton(modifier = Modifier.weight(1f), label = "", isOperation = true, onClick = {})
            CalcButton(modifier = Modifier.weight(1f), label = "0", onClick =  onNumPressed )
            CalcButton(modifier = Modifier.weight(1f), label = ",", onClick =  onNumPressed )
            CalcButton(
                modifier = Modifier.weight(1f),
                label = "=",
                isOperation = true,
                onClick = {})
        }
    }
}

@Composable
fun AddButton(modifier: Modifier = Modifier) {

}

@Preview(showBackground = true)
@Composable
fun CalcScreenPreview() {
    CalculatorApp()
}