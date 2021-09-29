package com.example.mynoteapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mynoteapp.model.Note
import java.util.concurrent.TimeUnit

/*
 A data access object (DAO) to map Kotlin functions to SQL queries
 When you use a Room database, you query the database by defining and
 calling Kotlin functions in your code. These Kotlin functions map to
 SQL queries. You define those mappings in a DAO using annotations, and
 Room creates the necessary code.
*/

@Dao
interface NoteDao {

    // it triggers whenever you try to insert an object that has the same @PrimaryKey
    // as an object of the same type that is already saved on the database. REPLACE
    // will overwrite the item in the database with the new item.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Note>>

    // A query is a request for data or information from a database table or
    // combination of tables, or a request to perform an action on the data
    @Query("SELECT * FROM notes WHERE noteTitle LIKE :query OR noteBody LIKE :query")
    fun searchNote(query: String?): LiveData<List<Note>>

    @Query("SELECT * FROM notes ORDER BY noteTitle ASC")
    fun sortNoteByTitleAZ(): LiveData<List<Note>>

    @Query("SELECT * FROM notes ORDER BY noteTitle DESC")
    fun sortNoteByTitleZA(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE isUpdated = 1 ORDER BY noteDateCreated ASC")
    fun sortNoteByUpdatedDateNewestFirst(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE isUpdated = 1 ORDER BY noteDateCreated DESC")
    fun sortNoteByUpdatedDateOldestFirst(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE isUpdated = 0 ORDER BY noteDateCreated ASC")
    fun sortNoteByCreatedDateNewestFirst(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE isUpdated = 0 ORDER BY noteDateCreated DESC")
    fun sortNoteByCreatedDateOldestFirst(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE isImportant = 1")
    fun filterImportantNote(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE isImportant = 0")
    fun filterNotImportantNote(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE :currentTime - noteDateCreated < :ONE_DAY_MILLIS")
    fun filterNoteByDayAgo(currentTime: Long, ONE_DAY_MILLIS: Long): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE :currentTime - noteDateCreated < :ONE_DAY_MILLIS*7")
    fun filterNoteByWeekAgo(currentTime: Long, ONE_DAY_MILLIS: Long): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE :currentTime - noteDateCreated < :ONE_DAY_MILLIS*30")
    fun filterNoteByMonthAgo(currentTime: Long, ONE_DAY_MILLIS: Long): LiveData<List<Note>>
}