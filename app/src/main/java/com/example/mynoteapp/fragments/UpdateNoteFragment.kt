package com.example.mynoteapp.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mynoteapp.MainActivity
import com.example.mynoteapp.R
import com.example.mynoteapp.databinding.FragmentUpdateNoteBinding
import com.example.mynoteapp.model.Note
import com.example.mynoteapp.toast
import com.example.mynoteapp.viewmodel.NoteViewModel

class UpdateNoteFragment : Fragment(R.layout.fragment_update_note) {

    private var _binding: FragmentUpdateNoteBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var currentNote: Note
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // inflate the Fragment's view
        // (which is equivalent to using setContentView() for an Activity)
        _binding = FragmentUpdateNoteBinding.inflate(
            inflater,
            container,
            false
        )

        // Get the viewModel
        noteViewModel = (activity as MainActivity).noteViewModel

        // Extract the arguments from the bundle
        val args = UpdateNoteFragmentArgs.fromBundle(requireArguments())
        currentNote = args.note!!

        // set title and body of note is updated
        binding.etNoteTitleUpdate.setText(currentNote.noteTitle)
        binding.etNoteBodyUpdate.setText(currentNote.noteBody)

        binding.fabDone.setOnClickListener {
            val title = binding.etNoteTitleUpdate.text.toString().trim()
            val body = binding.etNoteBodyUpdate.text.toString().trim()

            if (title.isNotEmpty()) {
                val note = Note(currentNote.id, title, body)
                noteViewModel.updateNote(note)

                activity?.toast("Note updated!")

                // Using directions to navigate to the HomeFragment
                view?.findNavController()?.navigate(
                    UpdateNoteFragmentDirections.actionUpdateNoteFragmentToHomeFragment()
                )
            } else {
                activity?.toast("Please enter title!")
            }
        }


        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.update_menu, menu)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}