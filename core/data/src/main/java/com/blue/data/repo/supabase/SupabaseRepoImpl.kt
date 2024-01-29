package com.blue.data.repo.supabase

import com.blue.data.repo.datastore.DataStoreRepo
import com.blue.data.work.status.RequestType
import com.blue.database.local.model.TodoEntity
import com.blue.model.Mandalart
import com.blue.supabase.model.MandalartModel
import com.blue.supabase.model.TodoModel
import com.blue.supabase.supabase.SupabaseDataSource
//import com.blue.work.status.RequestType
import io.github.jan.supabase.compose.auth.ComposeAuth
import javax.inject.Inject


class SupabaseRepoImpl @Inject constructor(
    private val supaDataSource: SupabaseDataSource,
    private val dataStoreRepo: DataStoreRepo,
    private val composeAuth: ComposeAuth,
) : SupabaseRepo {

    override fun getAuth(): ComposeAuth = composeAuth
    override fun getToken(): String? = supaDataSource.getToken()
    override suspend fun readUpdatedTodoData(): List<TodoModel> =
        supaDataSource.readUpdatedData(dataStoreRepo.getLastUpdateDateTime())
    override suspend fun insertTodoData(data: List<TodoEntity>): List<Long> =
        supaDataSource.insertTodoData(data.map {
            TodoModel(
                id = 0,
                local_id = it.id,
                update_date_time = it.updateDateTime,
                is_deleted = it.isDeleted,
                date = it.date,
                title = it.title,
                content = it.content,
                isDone = it.isDone
            )
        })

    override suspend fun deleteTodoData(id: List<Long>) =
        supaDataSource.deleteTodoData(id)

    override suspend fun insertMandalartData(data: Mandalart) =
        supaDataSource.insertMandalartData(
            MandalartModel(
                id = data.id,
                cnt = data.cnt
            )
        )

    override suspend fun deleteMandalartData(id: Long) =
        supaDataSource.deleteMandalartData(id)

    override suspend fun syncWith(typeData: RequestType): Boolean {
        return true
    }
}