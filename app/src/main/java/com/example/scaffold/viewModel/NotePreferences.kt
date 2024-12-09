package com.example.scaffold.viewModel

import android.content.Context
import android.content.SharedPreferences

class NotePreferences(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("notes_prefs", Context.MODE_PRIVATE)

    fun saveNotes(notes: List<String>) {
        prefs.edit().putStringSet("notes", notes.toSet()).apply()
    }

    fun loadNotes(): List<String> {
        return prefs.getStringSet("notes", emptySet())?.toList() ?: emptyList()
    }
}
