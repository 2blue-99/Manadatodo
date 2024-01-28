package com.blue.supabase.supabase

import android.util.Log
import com.blue.model.Mandalart
import com.blue.model.Todo
import com.blue.supabase.model.MandalartModel
import com.blue.supabase.model.TodoModel
import com.manadatodo.core.supabase.BuildConfig
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.composeAuth
import io.github.jan.supabase.compose.auth.googleNativeLogin
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.Postgrest
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


    override suspend fun insertTodo(data: TodoModel) {
        val result = client.postgrest["Todo"].insert(data)
        Log.e("TAG", "insertTodo: $result", )
    }

    override suspend fun deleteTodo(id: Long) {
        val result = client.from("Todo").delete{
            filter {
                TodoModel::row_id eq 666
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