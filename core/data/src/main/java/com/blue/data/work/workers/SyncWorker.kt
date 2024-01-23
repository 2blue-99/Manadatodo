package com.blue.data.work.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.tracing.traceAsync
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.blue.data.repo.database.TodoRepo
import com.blue.model.Todo
import com.blue.data.work.status.RequestType
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class SyncWorker @AssistedInject constructor(
    private val todoRepo: TodoRepo,
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
//    private val mandalartRepo: MandalartRepo,
//    private val dataStoreRepo: DataStoreRepo,
//    private val supabaseRepo: SupabaseRepo,
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        traceAsync("Sync", 0) {
            val type = inputData.getString("type")
            Log.e("TAG", "doWork: $type")
            Log.e("TAG", "doWork type: $type", )
        when(type){
            RequestType.TypeName.InsertTodo.name -> {
                val data = Todo(
                    date = inputData.getString("date")?:"",
                    title = inputData.getString("title")?:"",
                    content = inputData.getString("content")?:"",
                    isDone = inputData.getBoolean("isDone",false)
                )
                todoRepo.syncWith(RequestType.InsertTodo(data))
            }
            RequestType.TypeName.DeleteTodo.name -> {

            }
            RequestType.TypeName.InsertMandalart.name -> {}
            RequestType.TypeName.DeleteMandalart.name -> {}
        }

            Result.success()

//            val syncedSuccessfully = awaitAll(
//                async { todoRepo.sync() },
//            ).all { it }

//            if (syncedSuccessfully) {
//                Log.e("TAG", "doWork: 성공!")
//                Result.success()
//            } else {
//                Log.e("TAG", "doWork: 실패!")
//                Result.failure()
//            }
        }
    }
    companion object {
        const val KEY_WORKER = "key_worker"
    }
}