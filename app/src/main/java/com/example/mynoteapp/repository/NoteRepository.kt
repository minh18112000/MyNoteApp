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

}