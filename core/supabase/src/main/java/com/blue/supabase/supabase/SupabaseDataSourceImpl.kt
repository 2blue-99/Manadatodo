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

    override suspend fun upsertTodoData(data: List<TodoModel>): List<Long> {
        val result = client.postgrest["Todo"].upsert(data, onConflict = "id") {
            select()
        }.decodeList<TodoModel>()
        return result.map { it.id }
    }
    override suspend fun insertMandalartData(data: MandalartModel) {
        val result = client.postgrest["Mandalart"].insert(data)
    }


    override suspend fun readUpdatedData(date: String): List<TodoModel> =
        client.from("Todo").select {
            filter {
                TodoModel::update_date_time gt date
            }
        }.decodeList<TodoModel>()

    override suspend fun deleteMandalartData(id: Long) {
        val result = client.from("Mandalart").delete {
            filter {
                MandalartModel::id eq 666
                eq("id", id)
            }
        }
    }
}