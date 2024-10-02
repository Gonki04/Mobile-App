package com.example.calculator.ui.theme.Components

import android.health.connect.datatypes.units.Volume
import android.media.VolumeShaper.Operation
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.ui.theme.Pink40
import com.example.calculator.ui.theme.PurpleGrey40


@Composable
fun CalcButton(
    modifier: Modifier = Modifier,
    label : String = "",
    isOperation : Boolean = false,
    onClick : (String) -> Unit = {}
) {

    Button(
        modifier = modifier
            .width(80.dp)
            .height(50.dp)
            .aspectRatio(1f)
            .padding(4.dp),
        colors = if (isOperation)
            ButtonDefaults.run { buttonColors(Pink40) }
        else
            ButtonDefaults.run { buttonColors(PurpleGrey40) },
        onClick = {
            onClick(label)
        }) {
        Text(text = label,
            style = MaterialTheme.typography.bodyLarge)
    }

}

@Preview(showBackground = true)
@Composable
fun CalcButtonPreview() {
    CalculatorTheme {
        CalcButton(
            label = "7"
        )
    }
}