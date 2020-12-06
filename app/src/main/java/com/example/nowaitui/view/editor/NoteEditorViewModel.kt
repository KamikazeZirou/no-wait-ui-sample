package com.example.nowaitui.view.editor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.work.WorkManager
import com.example.nowaitui.worker.SaveWorker

class NoteEditorViewModel(
    application: Application,
): AndroidViewModel(application) {
    val title = MutableLiveData("")
    val description = MutableLiveData("")
    private val workManager = WorkManager.getInstance(application)

    fun save() {
        workManager.enqueue(SaveWorker.createRequest(title.value!!, description.value!!))
    }
}