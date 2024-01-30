package com.blue.supabase.supabase

import android.util.Log
import com.blue.supabase.model.MandalartModel
import com.blue.supabase.model.TodoModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SupabaseDataSourceImpl @Inject constructor(
    private val client: SupabaseClient
) : SupabaseDataSource {
    override fun getToken(): String? = client.auth.currentAccessTokenOrNull()
    override suspend fun readUpdatedData(date: String): List<TodoModel> =
        client.from("Todo").select {
            filter {
                TodoModel::update_date_time gt date
            }
        }.decodeList()


    override suspend fun insertTodoData(data: List<TodoModel>): List<Long> {
        Log.e("TAG", "insertTodoData: ${data}", )
        val result = client.postgrest["Todo"].insert(data){
            select()
        }.decodeList<TodoModel>().map { it.id }
        Log.e("TAG", "insertTodo: ${result}")
        return result
    }

    override suspend fun deleteTodoData(id: List<Long>) {
        val result = client.from("Todo").delete {
            filter {
                TodoModel::id eq 666
                eq("id", id)
            }
        }
        Log.e("TAG", "deleteTodo: $result")
    }

    override suspend fun insertMandalartData(data: MandalartModel) {
        val result = client.postgrest["Mandalart"].insert(data)
        Log.e("TAG", "insertMandalart: $result")
    }

    override suspend fun deleteMandalartData(id: Long) {
        val result = client.from("Mandalart").delete {
            filter {
                MandalartModel::id eq 666
                eq("id", id)
            }
        }
        Log.e("TAG", "deleteMandalart: $result")
    }
}