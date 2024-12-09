package com.example.scaffold

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import com.example.scaffold.ui.presentation.NoteApp
import com.example.scaffold.viewModel.NotePreferences
import com.example.scaffold.viewModel.NoteViewModel
import com.example.scaffold.viewModel.NoteViewModelFactory
import com.example.scaffold.ui.theme.ScaffoldTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var noteViewModel: NoteViewModel


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val notePrefs = NotePreferences(this)
        noteViewModel = ViewModelProvider(
            this,
            NoteViewModelFactory(notePrefs)
        )[NoteViewModel::class.java]
        setContent {
            ScaffoldTheme {
                Body(noteViewModel)
            }
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun Body(viewModel: NoteViewModel) {
        var selectedItem by remember { mutableStateOf("") }

        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {
                            Toast.makeText(this@MainActivity, "Меню открыто", Toast.LENGTH_SHORT)
                                .show()
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    title = { Text("Контакты") },
                    actions = {

                        IconButton(onClick = {
                            Toast.makeText(
                                this@MainActivity,
                                "Звонок совершен ($selectedItem)",
                                Toast.LENGTH_SHORT
                            ).show()
                        }) {
                            Icon(Icons.Default.Call, contentDescription = "Call")
                        }
                        IconButton(onClick = {
                            Toast.makeText(
                                this@MainActivity,
                                "Выход из приложения",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                        }) {
                            Icon(Icons.Default.Close, contentDescription = "Close")
                        }
                    }
                )
            },
            bottomBar = {
                BottomAppBar{
                    IconButton(onClick = {
                        Toast.makeText(
                            this@MainActivity,
                            "Сообщение отправлено ($selectedItem)",
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                        Icon(Icons.Default.Send, contentDescription = "Send")
                    }
                    IconButton(onClick = {
                        Toast.makeText(
                            this@MainActivity,
                            "Контакт отредактирован ($selectedItem)",
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit")
                    }
                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    if (viewModel.text.value.isNotEmpty()) {
                        viewModel.addNote()
                        viewModel.text.value = ""
                    }
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Добавить заметку")
                }
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)) {
                NoteApp(
                    viewModel = viewModel,
                    onItemSelected = { item ->
                        selectedItem = item
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

}
