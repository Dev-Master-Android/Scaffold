package com.example.scaffold.ui.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.scaffold.viewModel.NoteViewModel

@Composable
fun NoteApp(viewModel: NoteViewModel, onItemSelected: (String) -> Unit, modifier: Modifier = Modifier) {
    viewModel.text
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        OutlinedTextField(
            value =  viewModel.text.value,
            onValueChange = {  viewModel.text.value = it },
            label = { Text("Введите контакт") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(viewModel.notes) { note ->
                NoteItem(note = note, onDelete = { viewModel.deleteNote(note) }, onItemSelected = onItemSelected)
            }
        }
    }
}



