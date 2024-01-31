package com.blue.data.repo.database

import android.util.Log
import com.blue.data.mapper.Mapper.asTodoEntity
import com.blue.data.repo.datastore.DataStoreRepo
import com.blue.data.repo.supabase.SupabaseRepo
import com.blue.data.work.status.RequestType
import com.blue.data.work.status.SyncRequestRepo
import com.blue.database.local.dao.TodoDao
import com.blue.database.local.model.TodoEntity
import com.blue.database.local.model.asTodo
import com.blue.model.Todo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import javax.inject.Inject

class TodoRepoImpl @Inject constructor(
    private val todoDao: TodoDao,
    private val supabaseRepo: SupabaseRepo,
    private val syncRequest: SyncRequestRepo,
    private val dataStoreRepo: DataStoreRepo
) : TodoRepo {
    override suspend fun insertData(data: Todo) {
        val todoEntity = TodoEntity(
            id = data.id,
            supaId = data.supa_id,
            updateDateTime = LocalDateTime.now().toString(),
            isSynced = false,
            isDeleted = false,
            title = data.title,
            content = data.content,
            date = data.date,
            isDone = data.isDone
        )
        todoDao.upsertData(listOf(todoEntity))
        syncRequest.syncRequest()
    }


    override fun readAllDataFlow(): Flow<List<Todo>> =
        todoDao.readAllDataFlow().map {
            it.map { data -> data.asTodo() }
        }

    override fun readSelectedData(date: String): Flow<List<Todo>> =
        todoDao.readSelectedDataFlow(date).map {
            it.map { data -> data.asTodo() }
        }


    override suspend fun checkAndUpdateSupaData(): Boolean {

        val supaList = supabaseRepo.readUpdatedTodoData(dataStoreRepo.getLastUpdateDateTime())
        val localList = todoDao.readAllData()

        val supaIdList = supaList.map { it.id }

        val existLocalList = localList.filter { supaIdList.contains(it.supaId) }

        val existLocalListId = existLocalList.map { it.supaId }

        // Supa, Local 에 존재 - 수정, 삭제
        val updateData = existLocalList.map { local ->
            supaList.first { supa -> supa.id == local.supaId }.asTodoEntity(local.id)
        }
        // Supa 에만 존재 - 추가
        val insertData = supaList.filter { supa -> !existLocalListId.contains(supa.id) }
            .map { it.asTodoEntity() }

        todoDao.upsertData(updateData + insertData)
        return true
    }

    override suspend fun checkAndUpdateLocalData(): Boolean {

        val localList = todoDao.readToUpdateData(dataStoreRepo.getLastUpdateDateTime())

        val updateList = localList.filter { it.supaId != 0L && !it.isSynced }
        val insertData = localList.filter { it.supaId == 0L && !it.isSynced }

        // 삭제, 수정
        if (updateList.isNotEmpty()) {
            supabaseRepo.updateTodoData(updateList)
            updateList.forEach { it.isSynced = true }
        }
        // 추가
        if (insertData.isNotEmpty()) {
            val insertIdResult = supabaseRepo.insertTodoData(insertData)
            insertData.indices.forEach {
                insertData[it].supaId = insertIdResult[it]
                insertData[it].isSynced = true
            }
        }
        todoDao.upsertData(updateList + insertData)
        dataStoreRepo.updateLastUpdateDateTime(localList.maxOf { it.updateDateTime })
        return true
    }


    override suspend fun deleteData(id: List<Long>) {
        CoroutineScope(Dispatchers.IO).async { todoDao.deleteData(id, LocalDateTime.now().toString()) }.await()
        syncRequest.syncRequest()
    }

    override suspend fun changeCheckBox(id: Long, status: Boolean) {
        todoDao.changeCheckBox(id, status)
        syncRequest.syncRequest()
    }


    override suspend fun syncWith(typeData: RequestType): Boolean {
        return true
    }
}

