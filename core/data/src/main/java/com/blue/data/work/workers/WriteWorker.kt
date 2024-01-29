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
    private val supabaseRepo: SupabaseRepo,
    private val todoRepo: TodoRepo,
    private val dataStoreRepo: DataStoreRepo
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        Log.e("TAG", "WriteWorker doWork: 시작", )
        try {
            // TODO / Local DB에서 Last Update Time 이후의 값들을 전부 가져옴
            // Todo / 항목 존재 + note deleted: 추가, 추가
            // TODO / 항목 존재 + deleted : 삭제

            val localDataList = todoRepo.readToUpdateData(dataStoreRepo.getLastUpdateDateTime())
            Log.e("TAG", "localDataList: $localDataList", )

            val insertData = localDataList.filter { !it.isDeleted } // 수정 추가
            val deleteData = localDataList.filter { it.isDeleted }.map { it.supaId } // 삭제
            val resultIdList = supabaseRepo.insertTodoData(insertData)
            supabaseRepo.deleteTodoData(deleteData)

            if(resultIdList.size != insertData.size) Log.e("TAG", "doWork: 사이즈가 달라요!", )
            else insertData.indices.forEach{ insertData[it].supaId = resultIdList[it] }

            todoRepo.insertTodoEntitySyncData(insertData)

            Log.e("TAG", "doWork / last update time : ${dataStoreRepo.getLastUpdateDateTime()}", )
            dataStoreRepo.updateLastUpdateDateTime(LocalDateTime.now().toString())
            Log.e("TAG", "doWork / last update time : ${dataStoreRepo.getLastUpdateDateTime()}", )
            Log.e("TAG", "WriteWorker doWork: 종료", )
            Result.success()
        } catch (e: Exception) {
            Log.e("TAG", "doWork:  실패 $e", )
            Result.failure()
        }
    }
}