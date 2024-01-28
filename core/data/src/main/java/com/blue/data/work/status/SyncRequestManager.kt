package com.blue.data.work.status

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.blue.data.work.workers.SyncWorker
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SyncRequestManager @Inject constructor(
    @ApplicationContext private val context: Context
): SyncRequestInterface {

    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    override fun syncRequest(requestType: RequestType){
        when(requestType){
            is RequestType.InsertTodo -> {
                val data = Data.Builder()
                    .putString("type", RequestType.TypeName.InsertTodo.name)
                    .putLong("id", requestType.todo.id)
                    .putString("date", requestType.todo.date)
                    .putString("title", requestType.todo.title)
                    .putString("content", requestType.todo.content)
                    .putBoolean("isDone", requestType.todo.isDone)
                    .build()

                val uploadRequest = OneTimeWorkRequest.Builder(SyncWorker::class.java)
                    .setConstraints(constraints)
                    .setInputData(data)
                    .build()

                val uploadRequest2 = OneTimeWorkRequestBuilder<SyncWorker>()
                    .setConstraints(constraints)
                    .build()



                WorkManager.getInstance(context).apply {
                    enqueue(uploadRequest)
                    Log.e("TAG", "initSync: 정상 삽입", )
                }
            }
            is RequestType.DeleteTodo -> {}
            is RequestType.InsertMandalart -> {}
            is RequestType.DeleteMandalart -> {}
        }
    }
}