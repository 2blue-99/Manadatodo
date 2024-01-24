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

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val supabaseRepo: SupabaseRepo
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val type = inputData.getString("type")
            Log.e("TAG", "doWork type: $type")
            when (type) {
                RequestType.TypeName.InsertTodo.name -> {
                    val data = Todo(
                        date = inputData.getString("date") ?: "",
                        title = inputData.getString("title") ?: "",
                        content = inputData.getString("content") ?: "",
                        isDone = inputData.getBoolean("isDone", false)
                    )
                    supabaseRepo.insertTodo(data)
                    Result.success()
                }

                RequestType.TypeName.DeleteTodo.name -> {
                    val id = inputData.getInt("id", -1)
                    if (id == -1) Result.failure()
                    supabaseRepo.deleteTodo(id)
                    Result.success()
                }

                RequestType.TypeName.InsertMandalart.name -> {
                    val id = inputData.getInt("id", -1)
                    val cnt = inputData.getInt("cnt", -1)
                    if (id == -1 || cnt == -1) Result.failure()
                    supabaseRepo.insertMandalart(Mandalart(id, cnt))
                    Result.success()
                }

                RequestType.TypeName.DeleteMandalart.name -> {
                    val id = inputData.getInt("id", -1)
                    if (id == -1) Result.failure()
                    supabaseRepo.deleteMandalart(id)
                    Result.success()
                }

                else -> {
                    Log.e("TAG", "doWork: 실패")
                    Result.failure()
                }
            }
        } catch (e: Exception) {
            Log.e("TAG", "doWork: $e", )
            Result.failure()
        }
    }

    companion object {
        const val KEY_WORKER = "key_worker"
    }
}