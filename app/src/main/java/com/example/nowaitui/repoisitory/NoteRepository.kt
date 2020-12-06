package com.example.nowaitui.repoisitory

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.nowaitui.model.Note
import com.example.nowaitui.service.RemoteNoteService

class NoteRepository(private val service: RemoteNoteService) {
    fun get(): List<Note> {
        return service.get()
    }

    fun add(note: Note) {
        service.add(note)
    }
}