package com.blue.data.repo.supabase

import com.blue.data.work.status.RequestType
import com.blue.model.Mandalart
import com.blue.model.Todo
import com.blue.supabase.model.MandalartModel
import com.blue.supabase.model.TodoModel
import com.blue.supabase.model.toTodo
import com.blue.supabase.supabase.SupabaseDataSource
//import com.blue.work.status.RequestType
import io.github.jan.supabase.compose.auth.ComposeAuth
import javax.inject.Inject


class SupabaseRepoImpl @Inject constructor(
    private val dataSource: SupabaseDataSource,
    private val composeAuth: ComposeAuth,
) : SupabaseRepo {

    override fun getAuth(): ComposeAuth = composeAuth
    override fun getToken(): String? = dataSource.getToken()
    override suspend fun readTodo(date: String): List<TodoModel> =
        dataSource.readTodo(date)
    override suspend fun insertTodo(data: Todo) =
        dataSource.insertTodo(
            TodoModel(
                row_id = data.id,
                date = data.date,
                title = data.title,
                content = data.content,
                isDone = data.isDone
            )
        )

    override suspend fun deleteTodo(id: Long) =
        dataSource.deleteTodo(id)

    override suspend fun insertMandalart(data: Mandalart) =
        dataSource.insertMandalart(
            MandalartModel(
                id = data.id,
                cnt = data.cnt
            )
        )

    override suspend fun deleteMandalart(id: Long) =
        dataSource.deleteMandalart(id)

    override suspend fun syncWith(typeData: RequestType): Boolean {
        return true
    }
}