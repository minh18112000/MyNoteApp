package com.example.mynoteapp.repository

import com.example.mynoteapp.database.NoteDatabase
import com.example.mynoteapp.model.Note

class NoteRepository(private val db: NoteDatabase) {

    suspend fun addNote(note: Note) = db.getNoteDao().addNote(note)

    suspend fun updateNote(note: Note) = db.getNoteDao().updateNote(note)

    suspend fun deleteNote(note: Note) = db.getNoteDao().deleteNote(note)

    fun getAllNotes() = db.getNoteDao().getAllNotes()

    fun searchNote(query: String?) = db.getNoteDao().searchNote(query)

    fun sortNoteByTitleAZ() = db.getNoteDao().sortNoteByTitleAZ()

    fun sortNoteByTitleZA() = db.getNoteDao().sortNoteByTitleZA()

    fun sortNoteByUpdatedDateNewestFirst() = db.getNoteDao().sortNoteByUpdatedDateNewestFirst()

    fun sortNoteByUpdatedDateOldestFirst() = db.getNoteDao().sortNoteByUpdatedDateOldestFirst()

    fun sortNoteByCreatedDateNewestFirst() = db.getNoteDao().sortNoteByCreatedDateNewestFirst()

    fun sortNoteByCreatedDateOldestFirst() = db.getNoteDao().sortNoteByCreatedDateOldestFirst()

    fun filterImportantNote() = db.getNoteDao().filterImportantNote()

    fun filterNotImportantNote() = db.getNoteDao().filterNotImportantNote()

    fun filterNoteByDayAgo(currentTime: Long, ONE_DAY_MILLIS: Long) =
        db.getNoteDao().filterNoteByDayAgo(currentTime, ONE_DAY_MILLIS)

    fun filterNoteByWeekAgo(currentTime: Long, ONE_WEEK_MILLIS: Long) =
        db.getNoteDao().filterNoteByWeekAgo(currentTime, ONE_WEEK_MILLIS)

    fun filterNoteByMonthAgo(currentTime: Long, ONE_MONTH_MILLIS: Long) =
        db.getNoteDao().filterNoteByMonthAgo(currentTime, ONE_MONTH_MILLIS)
}