package com.blue.data.repo.supabase

import android.util.Log
import com.blue.data.repo.datastore.DataStoreRepo
import com.blue.data.work.status.RequestType
import com.blue.database.local.model.TodoEntity
import com.blue.model.Mandalart
import com.blue.supabase.model.MandalartModel
import com.blue.supabase.model.TodoModel
import com.blue.supabase.supabase.SupabaseDataSource
//import com.blue.work.status.RequestType
import io.github.jan.supabase.compose.auth.ComposeAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject


class SupabaseRepoImpl @Inject constructor(
    private val supaDataSource: SupabaseDataSource,
    private val composeAuth: ComposeAuth
) : SupabaseRepo {

    override fun getAuth(): ComposeAuth = composeAuth
    override fun getToken(): String? = supaDataSource.getToken()
    override suspend fun readUpdatedTodoData(date: String): List<TodoModel> =
        supaDataSource.readUpdatedData(date)

    override suspend fun insertTodoData(data: List<TodoEntity>): List<Long> {
        return CoroutineScope(Dispatchers.IO).async {
            supaDataSource.insertTodoData(data.map {
                TodoModel(
                    update_date_time = it.updateDateTime,
                    is_deleted = it.isDeleted,
                    date = it.date,
                    title = it.title,
                    content = it.content,
                    isDone = it.isDone,
                )
            })
        }.await()
    }

    override suspend fun deleteTodoData(id: List<TodoEntity>): Boolean {
        supaDataSource.deleteTodoData(id.map{
            TodoModel(
                id = it.supaId,
                update_date_time = LocalDateTime.now().toString(),
                is_deleted = true,
                date = it.date,
                title = it.title,
                content = it.content,
                isDone = it.isDone,
            )
        })
        return true
    }

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