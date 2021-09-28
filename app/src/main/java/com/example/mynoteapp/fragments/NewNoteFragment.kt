package com.example.mynoteapp.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mynoteapp.MainActivity
import com.example.mynoteapp.R
import com.example.mynoteapp.databinding.FragmentNewNoteBinding
import com.example.mynoteapp.model.Note
import com.example.mynoteapp.toast
import com.example.mynoteapp.viewmodel.NoteViewModel
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*


class NewNoteFragment : Fragment(R.layout.fragment_new_note) {

    // create a binding object
    private var _binding: FragmentNewNoteBinding? = null
    // This property is only valid between onCreateView and onDestroyView
    private val binding
        get() = _binding!!

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // inflate the Fragment's view
        // (which is equivalent to using setContentView() for an Activity)
        _binding = FragmentNewNoteBinding.inflate(inflater, container, false)

        // Get the viewModel
//        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel = (activity as MainActivity).noteViewModel

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mView = view
    }

    private fun saveNote(view: View) {
        val noteTitle = binding.etNoteTitle.text.toString().trim()
        val noteBody = binding.etNoteBody.text.toString().trim()
        val currentDate = SimpleDateFormat("dd/MM hh:mm")
        val noteDateCreated = "Created: ${currentDate.format(Date())}"
        val isUpdated = 0
        val isImportant = binding.cbNoteImportant.isChecked
        val noteImportant: Int
        if(isImportant) {
            noteImportant = 1
        } else {
            noteImportant = 0
        }
        if (noteTitle.isNotEmpty()) {
            // Create new note
            val note = Note(0, noteTitle, noteBody, noteDateCreated, isUpdated, noteImportant)

            noteViewModel.addNote(note)
            Snackbar.make(
                view,
                "Note saved successfully",
                Snackbar.LENGTH_SHORT
            ).show()

            // Using directions to navigate to the HomeFragment
            view.findNavController().navigate(NewNoteFragmentDirections.actionNewNoteFragmentToHomeFragment())
        } else {
            activity?.toast("Please enter note title!")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.save_menu -> {
                saveNote(mView)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.new_note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}