package com.example.mynoteapp.fragments

import android.os.Bundle
import android.view.*
import android.view.animation.Transformation
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Transformations
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mynoteapp.MainActivity
import com.example.mynoteapp.R
import com.example.mynoteapp.databinding.FragmentHomeBinding
import com.example.mynoteapp.model.Note
import com.example.mynoteapp.viewmodel.NoteViewModel
import com.example.noteapp.adapter.NoteAdapter
import java.util.concurrent.TimeUnit

private val ONE_DAY_MILLIS = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)

class HomeFragment : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener {

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

        val mMenuSearch = menu.findItem(R.id.menu_search).actionView as SearchView
        mMenuSearch.isSubmitButtonEnabled = false
        mMenuSearch.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchNotes(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchNotes(newText)
        }
        return true
    }

    private fun searchNotes(query: String?) {
        val searchQuery = "%$query%"
        noteViewModel.searchNote(searchQuery).observe(this, { list ->
            noteAdapter.differ.submitList(list)
        })
    }

    private fun sortNoteByTitleAZ() {
        noteViewModel.sortNoteByTitleAZ().observe(viewLifecycleOwner, { note ->
            noteAdapter.differ.submitList(note)
        })
    }

    private fun sortNoteByTitleZA() {
        noteViewModel.sortNoteByTitleZA().observe(viewLifecycleOwner, { note ->
            noteAdapter.differ.submitList(note)
        })
    }

    private fun sortNoteByUpdatedDateNewestFirst() {
        noteViewModel.sortNoteByUpdatedDateNewestFirst()
            .observe(viewLifecycleOwner, { note ->
                noteAdapter.differ.submitList(note)
            })
    }

    private fun sortNoteByUpdatedDateOldestFirst() {
        noteViewModel.sortNoteByUpdatedDateOldestFirst()
            .observe(viewLifecycleOwner, { note ->
                noteAdapter.differ.submitList(note)
            })
    }

    private fun sortNoteByCreatedDateNewestFirst() {
        noteViewModel.sortNoteByCreatedDateNewestFirst()
            .observe(viewLifecycleOwner, { note ->
                noteAdapter.differ.submitList(note)
            })
    }

    private fun sortNoteByCreatedDateOldestFirst() {
        noteViewModel.sortNoteByCreatedDateOldestFirst()
            .observe(viewLifecycleOwner, { note ->
                noteAdapter.differ.submitList(note)
            })
    }

    private fun filterImportantNote() {
        noteViewModel.filterImportantNote().observe(viewLifecycleOwner, { note ->
            noteAdapter.differ.submitList(note)
        })
    }

    private fun filterNotImportantNote() {
        noteViewModel.filterNotImportantNote().observe(viewLifecycleOwner, { note ->
            noteAdapter.differ.submitList(note)
        })
    }

    private fun filterNoteByDayAgo() {
        val currentTime = System.currentTimeMillis()
        noteViewModel.filterNoteByDayAgo(currentTime, ONE_DAY_MILLIS)
            .observe(viewLifecycleOwner, { note ->
                noteAdapter.differ.submitList(note)
            })
    }

    private fun filterNoteByWeekAgo() {
        val currentTime = System.currentTimeMillis()
        noteViewModel.filterNoteByWeekAgo(currentTime, ONE_DAY_MILLIS)
            .observe(viewLifecycleOwner, { note ->
                noteAdapter.differ.submitList(note)
            })
    }

    private fun filterNoteByMonthAgo() {
        val currentTime = System.currentTimeMillis()
        noteViewModel.filterNoteByMonthAgo(currentTime, ONE_DAY_MILLIS)
            .observe(viewLifecycleOwner, { note ->
                noteAdapter.differ.submitList(note)
            })
    }

    private fun noSort() {
        noteViewModel.getAllNotes().observe(viewLifecycleOwner, { note ->
            noteAdapter.differ.submitList(note)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort_by_title_az -> sortNoteByTitleAZ()
            R.id.sort_by_title_za -> sortNoteByTitleZA()
            R.id.sort_by_updated_date_newest_first -> sortNoteByUpdatedDateNewestFirst()
            R.id.sort_by_updated_date_oldest_first -> sortNoteByUpdatedDateOldestFirst()
            R.id.sort_by_created_date_newest_first -> sortNoteByCreatedDateNewestFirst()
            R.id.sort_by_created_date_oldest_first -> sortNoteByCreatedDateOldestFirst()
            R.id.filter_by_important_note -> filterImportantNote()
            R.id.filter_by_not_important_note -> filterNotImportantNote()
            R.id.filter_by_date_created_day_ago -> filterNoteByDayAgo()
            R.id.filter_by_date_created_week_ago -> filterNoteByWeekAgo()
            R.id.filter_by_date_created_month_ago -> filterNoteByMonthAgo()
            R.id.no_sort -> noSort()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}