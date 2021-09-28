package com.example.mynoteapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

// An entity represents an object or a concept, along with its properties,
// to store in the database. The entity class has mappings to tell Room
// how it intends to present and interact with the information in the database.
@Entity(tableName = "notes")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val noteTitle: String,
    val noteBody: String,
    val noteDateCreated: String,
    // 1 == TRUE, 0 == FALSE
    val isUpdated: Int
) : Parcelable