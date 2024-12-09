package com.example.scaffold.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class NoteViewModel(private val notePrefs: NotePreferences) : ViewModel() {
    var text = mutableStateOf("")
    private val _notes = mutableStateListOf<String>()
    val notes: List<String> = _notes

    init {
        _notes.addAll(notePrefs.loadNotes())
    }

    fun addNote() {
        _notes.add(text.value)
        notePrefs.saveNotes(_notes)
    }

    fun deleteNote(note: String) {
        _notes.remove(note)
        notePrefs.saveNotes(_notes)
    }
}
