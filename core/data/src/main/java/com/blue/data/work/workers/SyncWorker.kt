package com.blue.data.work.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.blue.data.repo.database.TodoRepo
import com.blue.data.repo.datastore.DataStoreRepo
import com.blue.data.repo.supabase.SupabaseRepo
import com.blue.database.local.model.TodoEntity
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
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
    private val supabaseRepo: SupabaseRepo,
    private val todoRepo: TodoRepo,
    private val dataStoreRepo: DataStoreRepo
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            Log.e("TAG", "syncWorker doWork: ", )
            val supaDataList = supabaseRepo.readTodo(dataStoreRepo.getLastUpdateDateTime())
            val localDataList = todoRepo.readAllDataFlow().first()
            // TODO, Supa DB에 존재하는 id와 Local DB에 존재하는 Supa_id 값들을 비교
            // Todo, local DB에 존재하지 않는 ID 가 Supa DB id에 있다면 Local DB에 추가해야함
            val insertIdList = localDataList.map { it.supaId }.toSet() - supaDataList.map { it.id }.toSet()
            val inputList = mutableListOf<TodoEntity>()

            for(i in supaDataList){
                if(insertIdList.contains(i.id))
                    inputList.add(TodoEntity(

                    ))
            }
            supaDataList.filter { it.id == inputList }

            insertIdList.forEach{ inputList.add(supaDataList }
            todoRepo.insertData(supaDataList)
            Result.success()
        } catch (e: Exception) {
            Log.e("TAG", "doWork: $e")
            Result.failure()
        }
    }

    companion object {
        const val KEY_WORKER = "key_worker"
    }
}