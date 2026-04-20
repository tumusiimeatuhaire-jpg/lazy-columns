package com.example.lazycolumns

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Item(val id: Int, val name: String, val description: String)

val itemList = List(20) { i ->
    Item(i, "Item $i", "Description for item $i")
}

@Composable
fun ItemList(items: List<Item>) {
    LazyColumn {
        items(items) { item ->
            ItemRow(item)
        }
    }
}

@Composable
fun ItemRow(item: Item) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = item.name, style = MaterialTheme.typography.titleLarge)
        Text(text = item.description, style = MaterialTheme.typography.bodyMedium)
    }
}
