package com.example.nowaitui.worker

import android.content.Context
import android.os.SystemClock
import androidx.work.*
import com.example.nowaitui.repoisitory.NoteRepository
import com.example.nowaitui.service.RemoteNoteService
import com.example.nowaitui.model.Note

class SaveWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    override fun doWork(): Result {
        SystemClock.sleep(5 * 1000)

        val title = inputData.getString(KEY_TITLE) ?: ""
        val description = inputData.getString(KEY_DESCRIPTION) ?: ""

        val noteRepository = NoteRepository(
            RemoteNoteService(applicationContext)
        )

        noteRepository.add(
            Note(
                title,
                description
            )
        )

        return Result.success()
    }

    companion object {
        const val TAG: String = "SaveWorker"
        private const val KEY_TITLE = "title"
        private const val KEY_DESCRIPTION = "description"

        fun createRequest(title: String, description: String): OneTimeWorkRequest {
            val data = Data.Builder()
                .putString(KEY_TITLE, title)
                .putString(KEY_DESCRIPTION, description)
                .build()

            return OneTimeWorkRequestBuilder<SaveWorker>()
                .setInputData(data)
                .addTag(TAG)
                .build()
        }
    }
}