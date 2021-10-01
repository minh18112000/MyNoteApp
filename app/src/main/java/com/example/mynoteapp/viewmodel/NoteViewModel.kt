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

    fun sortNoteByUpdatedDateNewestFirst() = noteRepository.sortNoteByUpdatedDateNewestFirst()

    fun sortNoteByUpdatedDateOldestFirst() = noteRepository.sortNoteByUpdatedDateOldestFirst()

    fun sortNoteByCreatedDateNewestFirst() = noteRepository.sortNoteByCreatedDateNewestFirst()

    fun sortNoteByCreatedDateOldestFirst() = noteRepository.sortNoteByCreatedDateOldestFirst()

    fun filterImportantNote() = noteRepository.filterImportantNote()

    fun filterNotImportantNote() = noteRepository.filterNotImportantNote()

    fun filterNoteByDayAgo(currentTime: Long, ONE_DAY_MILLIS: Long) =
        noteRepository.filterNoteByDayAgo(currentTime, ONE_DAY_MILLIS)

    fun filterNoteByWeekAgo(currentTime: Long, ONE_WEEK_MILLIS: Long) =
        noteRepository.filterNoteByWeekAgo(currentTime, ONE_WEEK_MILLIS)

    fun filterNoteByMonthAgo(currentTime: Long, ONE_MONTH_MILLIS: Long) =
        noteRepository.filterNoteByMonthAgo(currentTime, ONE_MONTH_MILLIS)
}