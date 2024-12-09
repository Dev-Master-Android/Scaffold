package com.example.scaffold.ui.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NoteItem(note: String, onDelete: () -> Unit, onItemSelected: (String) -> Unit) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clickable { onItemSelected(note) },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = note)
        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Удалить заметку"
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun NoteItemPreview() {
    NoteItem(note = "Текст заметки", onDelete = {}, onItemSelected = {})
}
