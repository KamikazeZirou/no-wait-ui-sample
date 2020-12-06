package com.example.nowaitui.view.notes

import android.app.Application
import androidx.lifecycle.*
import androidx.work.WorkManager
import com.example.nowaitui.repoisitory.NoteRepository
import com.example.nowaitui.service.RemoteNoteService
import com.example.nowaitui.worker.SaveWorker
import com.example.nowaitui.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val workManager = WorkManager.getInstance(application)

    private val repository = NoteRepository(
        RemoteNoteService(application)
    )

    private val _notes = MutableLiveData<List<Note>>(listOf())
    val notes: LiveData<List<Note>> = _notes

    val saveWorkerInfos = workManager.getWorkInfosByTagLiveData(SaveWorker.TAG)

    fun reload() {
        viewModelScope.launch(Dispatchers.IO) {
            _notes.postValue(repository.get())
        }
    }
}