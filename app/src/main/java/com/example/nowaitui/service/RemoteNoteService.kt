package com.example.nowaitui.service

import android.content.Context
import com.example.nowaitui.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.InputStream

class RemoteNoteService(private val context: Context) {
    fun get(): List<Note> {
        val results = mutableListOf<Note>()
        val file = File(context.filesDir, "notes.txt")
        if (file.exists()) {
            file.bufferedReader().use {
                it.forEachLine { line ->
                    val (title, description) = line.split("\t")
                    results.add(Note(title, description))
                }
            }
        }
        return results
    }

    fun add(note: Note) {
        val file = File(context.filesDir, "notes.txt")
        file.appendText("${note.title}\t${note.description}\n")
    }
}