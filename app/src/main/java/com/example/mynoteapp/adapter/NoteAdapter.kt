package com.example.noteapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mynoteapp.databinding.NoteLayoutAdapterBinding
import com.example.mynoteapp.fragments.HomeFragmentDirections
import com.example.mynoteapp.model.Note
import java.text.SimpleDateFormat
import java.util.*


class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(val itemBinding: NoteLayoutAdapterBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback =
        object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }

        }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            NoteLayoutAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        // Get current note
        val currentNote = differ.currentList[position]
        val sdf = SimpleDateFormat("dd/MM hh:mm")
        val resultDate = Date(currentNote.noteDateCreated)

        // Set note on RecycleView
        holder.itemBinding.tvNoteTitle.text = currentNote.noteTitle
        holder.itemBinding.tvNoteBody.text = currentNote.noteBody
        holder.itemBinding.tvNoteDateCreated.text = sdf.format(resultDate)
        if(currentNote.isImportant == 1) {
            holder.itemBinding.imgNoteImportant.visibility = View.VISIBLE
        } else {
            holder.itemBinding.imgNoteImportant.visibility = View.GONE
        }
        val random = Random()
        val color = Color.argb(
            255,
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256)
        )

        holder.itemBinding.ibColor.setBackgroundColor(color)

        holder.itemView.setOnClickListener {
            val direction = HomeFragmentDirections
                .actionHomeFragmentToUpdateNoteFragment(currentNote)
            it.findNavController().navigate(direction)
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}