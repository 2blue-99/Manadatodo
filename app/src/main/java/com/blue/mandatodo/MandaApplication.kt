package com.blue.mandatodo

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.blue.data.repo.database.MandalartRepo
import com.blue.data.repo.database.TodoRepo
import com.blue.data.repo.datastore.DataStoreRepo
import com.blue.data.repo.supabase.SupabaseRepo
import com.blue.data.work.init.Sync
import com.blue.data.work.workers.SyncWorker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class MandaApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var hiltWorkerFactory: CustomWorkerFactory
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder().setMinimumLoggingLevel(Log.DEBUG)
            .setWorkerFactory(hiltWorkerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()
        Sync.firstSyncRequest(applicationContext)
    }
}

class CustomWorkerFactory @Inject constructor(
    private val supabaseRepo: SupabaseRepo,
    private val dataStoreRepo: DataStoreRepo
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters,
    ): ListenableWorker =
        SyncWorker(
            appContext = appContext,
            workerParams = workerParameters,
            supabaseRepo = supabaseRepo,
            dataStoreRepo = dataStoreRepo
        )

}