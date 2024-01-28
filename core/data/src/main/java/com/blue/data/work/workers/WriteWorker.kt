package com.blue.data.work.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.tracing.traceAsync
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.blue.data.repo.database.TodoRepo
import com.blue.data.repo.supabase.SupabaseRepo
import com.blue.model.Todo
import com.blue.data.work.status.RequestType
import com.blue.model.Mandalart
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
// WriteWorker
// Local DB의 변동 사항을 Supa DB에 전달하는 Worker
// L U T 이후의 Local DB 값들을 전부 가져옴
// 추가, 삭제, 수정
// Local DB의 값들 중, is_synced = false 값들을 supa db에 보냄
@HiltWorker
class WriteWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,

) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {


            Result.success()
        } catch (e: Exception) {
            Log.e("TAG", "doWork: $e", )
            Result.failure()
        }
    }

    companion object {
        const val KEY_WORKER = "key_worker"
    }
}