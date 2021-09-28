package com.example.mynoteapp.viewmodel

import android.app.Application
import android.app.DownloadManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynoteapp.model.Note
import com.example.mynoteapp.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(
    app: Application,
    private val noteRepository: NoteRepository
) : AndroidViewModel(app) {

    fun addNote(note: Note) = viewModelScope.launch {
        noteRepository.addNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        noteRepository.updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteRepository.deleteNote(note)
    }

    fun getAllNotes() = noteRepository.getAllNotes()

    fun searchNote(query: String?) = noteRepository.searchNote(query)

    fun sortNoteByTitleAZ() = noteRepository.sortNoteByTitleAZ()

    fun sortNoteByTitleZA() = noteRepository.sortNoteByTitleZA()
}