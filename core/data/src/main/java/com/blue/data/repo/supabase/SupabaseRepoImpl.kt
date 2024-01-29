package com.blue.data.repo.supabase

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
    private val composeAuth: ComposeAuth,
) : SupabaseRepo {

    override fun getAuth(): ComposeAuth = composeAuth
    override fun getToken(): String? = supaDataSource.getToken()
    override suspend fun readTodo(date: String): List<TodoModel> =
        supaDataSource.readTodo(date)

    override suspend fun insertTodo(data: List<TodoEntity>): List<Long> =
        supaDataSource.insertTodo(data.map {
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

    override suspend fun deleteTodo(id: List<Long>) =
        supaDataSource.deleteTodo(id)

    override suspend fun insertMandalart(data: Mandalart) =
        supaDataSource.insertMandalart(
            MandalartModel(
                id = data.id,
                cnt = data.cnt
            )
        )

    override suspend fun deleteMandalart(id: Long) =
        supaDataSource.deleteMandalart(id)

    override suspend fun syncWith(typeData: RequestType): Boolean {
        return true
    }
}