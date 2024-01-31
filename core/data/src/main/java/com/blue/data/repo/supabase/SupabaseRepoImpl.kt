package com.blue.data.repo.supabase

import com.blue.data.mapper.Mapper.asTodoModel
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
import javax.inject.Inject


class SupabaseRepoImpl @Inject constructor(
    private val supaDataSource: SupabaseDataSource,
    private val composeAuth: ComposeAuth
) : SupabaseRepo {

    override fun getAuth(): ComposeAuth = composeAuth
    override fun getToken(): String? = supaDataSource.getToken()


    override suspend fun insertTodoData(data: List<TodoEntity>): List<Long> {
        return CoroutineScope(Dispatchers.IO).async {
            supaDataSource.upsertTodoData(data.map {
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

    override suspend fun insertMandalartData(data: Mandalart) =
        supaDataSource.insertMandalartData(
            MandalartModel(
                id = data.id,
                cnt = data.cnt
            )
        )


    override suspend fun readUpdatedTodoData(date: String): List<TodoModel> =
        supaDataSource.readUpdatedData(date)


    override suspend fun updateTodoData(list: List<TodoEntity>): Boolean {
        //수정, 삭제로 나누기
        val deleteList = list.filter { it.isDeleted }
            .map { it.asTodoModel(isDeleted = true) }
        val updateList = list.filter { !it.isDeleted }
            .map { it.asTodoModel(isDeleted = false) }
        supaDataSource.upsertTodoData(deleteList+updateList)
        return true
    }

    override suspend fun deleteMandalartData(id: Long) =
        supaDataSource.deleteMandalartData(id)


    override suspend fun syncWith(typeData: RequestType): Boolean {
        return true
    }
}