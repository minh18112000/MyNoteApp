package com.example.mynoteapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mynoteapp.repository.NoteRepository
import java.lang.IllegalArgumentException

class NoteViewModelFactory(val app: Application, private val noteRepository: NoteRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            return NoteViewModel(app,noteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}