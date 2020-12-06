package com.example.nowaitui.view.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nowaitui.databinding.NoteBinding
import com.example.nowaitui.model.Note

class NotesAdapter(
    private val viewModel: NotesViewModel
) : ListAdapter<Note, NotesAdapter.ContentViewHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder =
        ContentViewHolder.from(parent)

    override fun onBindViewHolder(contentHolder: ContentViewHolder, position: Int) {
        val item = getItem(position)
        contentHolder.bind(viewModel, item)
    }

    class ContentViewHolder(private val binding: NoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: NotesViewModel, item: Note) {
            binding.note = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ContentViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NoteBinding.inflate(layoutInflater, parent, false)
                return ContentViewHolder(binding)
            }
        }
    }
}

private class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        // Do not imitate. Compare by ID, etc.
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}