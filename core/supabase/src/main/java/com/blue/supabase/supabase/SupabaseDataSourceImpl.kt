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
        client.from("Help").select {
            filter {
                TodoModel::update_date_time gt date
            }
        }.decodeList<TodoModel>().also { Log.e("TAG", "readUpdatedData: $it") }


    override suspend fun insertTodoData(data: List<TodoModel>): List<Long> {
        Log.e("TAG", "insertTodoData: ${data}")
        val result = client.postgrest["Help"].upsert(data, onConflict = "id") {
            select()
        }.decodeList<TodoModel>()
        val supaIdList = result.map { it.id }
        Log.e("TAG", "gap: $supaIdList")
        return supaIdList
    }

    override suspend fun deleteTodoData(list: List<TodoModel>) {
        Log.e("TAG", "deleteTodoData: $list")
        val result = client.from("Help").upsert(list, onConflict = "id")
//        val result = client.from("Todo").update(list) {
//            filter {
//                eq("id", list.first().id)
//            }
//        }
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