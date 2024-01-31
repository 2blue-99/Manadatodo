package com.blue.data.work.status

import android.content.Context
import android.util.Log
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