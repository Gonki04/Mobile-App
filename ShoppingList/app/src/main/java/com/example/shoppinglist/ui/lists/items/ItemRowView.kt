package com.example.shoppinglist.ui.lists.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.models.Item
import com.example.shoppinglist.ui.theme.ShoppingListTheme


@Composable
fun ItemRomView(
    modifier: Modifier = Modifier,
    item: Item,
    onCheckedChange: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.medium
            )
            .padding(16.dp)
            .shadow(4.dp, shape = MaterialTheme.shapes.medium)
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            text = item.name ?: "",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )

        Text(
            modifier = Modifier
                .padding(16.dp),
            text = item.qtd.toString(),
            style = MaterialTheme.typography.bodyMedium.copy(fontStyle = FontStyle.Normal)
        )

        // Checkbox
        Checkbox(
            checked = item.checked,
            onCheckedChange = { onCheckedChange() },
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ItemRomViewPreview(){
    ShoppingListTheme {
        ItemRomView(item = Item(
            docId = "",
            name = "Lápis",
            qtd = 2,
            checked = false
        )
        )
    }
}