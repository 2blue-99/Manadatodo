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

// SyncWorker
// Supa DB의 변동 사항을 Local DB에 전달하는 Worker
// Last Update Time 이후의 Supa DB값들을 전부 가져옴
// 추가, 삭제, 수정
// supa_id와 local_id 비교
// -> Local db에 없는 항목 = 추가
// -> Local db에 있는 항목 = 수정
@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val supabaseRepo: SupabaseRepo
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