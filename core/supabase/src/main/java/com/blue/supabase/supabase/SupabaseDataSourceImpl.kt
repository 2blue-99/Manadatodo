package com.blue.supabase.supabase

import android.util.Log
import com.blue.supabase.model.MandalartModel
import com.blue.supabase.model.TodoModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SupabaseDataSourceImpl @Inject constructor(
    private val client: SupabaseClient
) : SupabaseDataSource {
    override fun getToken(): String? = client.auth.currentAccessTokenOrNull()
    override suspend fun readTodo(date: String) : List<TodoModel>
        = client.from("Todo").select{
            filter {
                TodoModel::date eq date
//                eq("date",date)
            }
        }.decodeList()


    override suspend fun insertTodo(data: List<TodoModel>): List<Long> {
        val result = client.postgrest["Todo"].insert(data)
//        val result = client.postgrest["Todo"].upsert(data,onConflict = "id")
        Log.e("TAG", "insertTodo: $result", )
        return result.decodeList()
    }

    override suspend fun deleteTodo(id: List<Long>) {
        val result = client.from("Todo").delete{
            filter {
                TodoModel::local_id eq 666
                eq("id", id)
            }
        }
        Log.e("TAG", "deleteTodo: $result", )
    }

    override suspend fun insertMandalart(data: MandalartModel) {
        val result = client.postgrest["Mandalart"].insert(data)
        Log.e("TAG", "insertMandalart: $result", )
    }

    override suspend fun deleteMandalart(id: Long) {
        val result = client.from("Mandalart").delete{
            filter {
                MandalartModel::id eq 666
                eq("id",id)
            }
        }
        Log.e("TAG", "deleteMandalart: $result", )
    }
}