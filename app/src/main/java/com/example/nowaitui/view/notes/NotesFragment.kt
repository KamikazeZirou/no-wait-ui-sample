package com.example.nowaitui.view.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nowaitui.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NotesFragment : Fragment() {
    private val viewModel by viewModels<NotesViewModel>()

    private val notesAdapter by lazy {
        NotesAdapter(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesAdapter
        }

        viewModel.reload()

        viewModel.saveWorkerInfos.observe(viewLifecycleOwner) { listOfWorkInfo ->
            val workInfo = listOfWorkInfo.firstOrNull()
            if (workInfo?.state?.isFinished == true) {
                viewModel.reload()
            }
        }

        viewModel.notes.observe(viewLifecycleOwner) {
            notesAdapter.submitList(it)
        }
    }
}
