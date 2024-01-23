package com.blue.work.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.tracing.traceAsync
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.blue.data.Synchronizer
import com.blue.data.repo.database.MandalartRepo
import com.blue.data.repo.database.TodoRepo
import com.blue.data.repo.datastore.DataStoreRepo
import com.blue.data.repo.supabase.SupabaseRepo
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltWorker
class SyncWorker @AssistedInject constructor(
    private val todoRepo: TodoRepo,
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
//    private val mandalartRepo: MandalartRepo,
//    private val dataStoreRepo: DataStoreRepo,
//    private val supabaseRepo: SupabaseRepo,
) : CoroutineWorker(appContext, workerParams), Synchronizer {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        traceAsync("Sync", 0) {
            val cnt = inputData.getInt("test", -1)
//            val outPut = Data.Builder().putInt(KEY_WORKER, cnt).build()
            Log.e("TAG", "doWork: $cnt")
//        when(outPut){
//            1 -> {}
//            2 -> {}
//            3 -> {}
//            else -> {}
//        }
            val syncedSuccessfully = awaitAll(
                async { todoRepo.sync() },
            ).all { it }

            if (syncedSuccessfully) {
                Log.e("TAG", "doWork: 성공!")
                Result.success()
            } else {
                Log.e("TAG", "doWork: 실패!")
                Result.failure()
            }
        }
    }
    companion object {
        const val KEY_WORKER = "key_worker"
    }
}