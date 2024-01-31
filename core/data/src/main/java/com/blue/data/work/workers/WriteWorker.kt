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
import io.ktor.util.Identity.decode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

// WriteWorker
// Local DB의 변동 사항을 Supa DB에 전달하는 Worker
// L U T 이후의 Local DB 값들을 전부 가져옴
// 추가, 삭제, 수정
// Local DB의 값들 중, is_synced = false 값들을 supa db에 보냄
@HiltWorker
class WriteWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
//    private val supabaseRepo: SupabaseRepo,
    private val todoRepo: TodoRepo,
//    private val dataStoreRepo: DataStoreRepo
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            todoRepo.checkAndUpdateLocalData()
            // TODO Last Update Time 만다, todo 각자 다르게 관리해야함.
            Log.e("TAG", "WriteWorker doWork: 성공", )
            Result.success()
        } catch (e: Exception) {
            Log.e("TAG", "doWork:  실패 $e", )
            Result.failure()
        }
    }
}