package com.blue.work.initializers

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.blue.work.workers.SyncWorker

object Sync {
    fun initSync(context: Context) {
        val data = Data.Builder().putInt("test", 777).build()

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val uploadRequest = OneTimeWorkRequest.Builder(SyncWorker::class.java)
            .setConstraints(constraints)
            .setInputData(data)
            .build()

        WorkManager.getInstance(context).apply {
            enqueue(uploadRequest)
            Log.e("TAG", "initSync: 정상 삽입", )
        }
    }
}