package com.blue.data.work.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.blue.data.repo.database.TodoRepo
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val todoRepo: TodoRepo,
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            todoRepo.checkAndUpdateSupaData()
            Log.e("TAG", "SyncWorker doWork: 성공", )
            Result.success()
        } catch (e: Exception) {
            Log.e("TAG", "doWork: 실패 $e")
            Result.failure()
        }
    }
}