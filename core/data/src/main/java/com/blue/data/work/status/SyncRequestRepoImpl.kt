package com.blue.data.work.status

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.blue.data.work.init.Sync
import com.blue.data.work.workers.SyncWorker
import com.blue.data.work.workers.WriteWorker
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SyncRequestRepoImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SyncRequestRepo {

    override fun syncRequest() {

        val syncRequest = OneTimeWorkRequestBuilder<SyncWorker>()
            .setConstraints(Sync.constraints)
            .build()

        val writeRequest = OneTimeWorkRequestBuilder<WriteWorker>()
            .setConstraints(Sync.constraints)
            .build()


        // Todo 아래 코드는 write Request 가 쌓이는 것처럼 보임
        WorkManager.getInstance(context).beginUniqueWork(
            "Sync",
            ExistingWorkPolicy.KEEP,
            syncRequest
        ).then(writeRequest).enqueue()
    }
}


//        when(requestType){
//            is RequestType.InsertTodo -> {
//                val data = Data.Builder()
//                    .putString("type", RequestType.TypeName.InsertTodo.name)
//                    .putLong("id", requestType.todo.id)
//                    .putString("date", requestType.todo.date)
//                    .putString("title", requestType.todo.title)
//                    .putString("content", requestType.todo.content)
//                    .putBoolean("isDone", requestType.todo.isDone)
//                    .build()
//
//                val uploadRequest = OneTimeWorkRequest.Builder(SyncWorker::class.java)
//                    .setConstraints(constraints)
//                    .setInputData(data)
//                    .build()
//
//                val uploadRequest2 = OneTimeWorkRequestBuilder<SyncWorker>()
//                    .setConstraints(constraints)
//                    .build()
//
//
//
//                WorkManager.getInstance(context).apply {
//                    enqueue(uploadRequest)
//                    Log.e("TAG", "initSync: 정상 삽입", )
//                }
//            }
//            is RequestType.DeleteTodo -> {}
//            is RequestType.InsertMandalart -> {}
//            is RequestType.DeleteMandalart -> {}
//        }