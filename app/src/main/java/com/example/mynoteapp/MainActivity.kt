package com.example.mynoteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mynoteapp.database.NoteDatabase
import com.example.mynoteapp.databinding.ActivityMainBinding
import com.example.mynoteapp.repository.NoteRepository
import com.example.mynoteapp.viewmodel.NoteViewModel
import com.example.mynoteapp.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Use view binding in activities
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
    }

    private fun setUpViewModel() {
        val noteRepository = NoteRepository(NoteDatabase(this))

        val viewModelFactory = NoteViewModelFactory(application, noteRepository)

        noteViewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(NoteViewModel::class.java)
    }
}