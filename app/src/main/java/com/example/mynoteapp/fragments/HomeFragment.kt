package com.example.mynoteapp.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mynoteapp.MainActivity
import com.example.mynoteapp.R
import com.example.mynoteapp.databinding.FragmentHomeBinding
import com.example.mynoteapp.model.Note
import com.example.mynoteapp.viewmodel.NoteViewModel
import com.example.noteapp.adapter.NoteAdapter

class HomeFragment : Fragment(R.layout.fragment_home) {

    // create a binding object
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding
        get() = _binding!!

    // Create a ref of NoteViewModel
    private lateinit var noteViewModel: NoteViewModel

    private lateinit var noteAdapter: NoteAdapter

    // Done
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Use view binding in fragments
        // Inflate the layout for this fragment
        // (which is equivalent to using setContentView() for an Activity)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Get the viewModel
//        viewModelFactory = NoteViewModelProviderFactory(Home)
//        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel = (activity as MainActivity).noteViewModel

        setHasOptionsMenu(true)
        return binding.root
    }

    // Done
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

        // Using directions to navigate to the NewNoteFragment
        binding.fabAddNote.setOnClickListener {
            it.findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToNewNoteFragment())
        }

    }

    private fun setUpRecyclerView() {
        noteAdapter = NoteAdapter()

        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                1,
                StaggeredGridLayoutManager.VERTICAL
            )

            setHasFixedSize(true)
            adapter = noteAdapter
        }
        activity?.let {
            noteViewModel.getAllNotes().observe(viewLifecycleOwner, { note ->
                noteAdapter.differ.submitList(note)
                updateUi(note)
            })
        }
    }

    private fun updateUi(note: List<Note>) {
        if (note.isNotEmpty()) {
            binding.cardView.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        } else {
            binding.cardView.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}