package com.blue.data.work.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.blue.data.repo.database.TodoRepo
import com.blue.data.repo.datastore.DataStoreRepo
import com.blue.data.repo.supabase.SupabaseRepo
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
    private val todoRepo: TodoRepo,
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
//            val result = todoRepo.syncUpdateData()
//            Log.e("TAG", "doWork result : $result", )
            Log.e("TAG", "doWork: 성공", )
            Result.success()
        } catch (e: Exception) {
            Log.e("TAG", "doWork: 실패 $e")
            Result.failure()
        }
    }
}