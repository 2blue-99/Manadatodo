package com.blue.data.repo.database

import android.util.Log
import com.blue.data.mapper.Mapper.toIdTodoEntity
import com.blue.data.mapper.Mapper.todoEntity
import com.blue.data.repo.datastore.DataStoreRepo
import com.blue.data.repo.supabase.SupabaseRepo
import com.blue.data.work.status.RequestType
import com.blue.data.work.status.SyncRequestRepo
import com.blue.database.local.dao.TodoDao
import com.blue.database.local.model.TodoEntity
import com.blue.database.local.model.toTodo
import com.blue.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class TodoRepoImpl @Inject constructor(
    private val todoDao: TodoDao,
    private val supabaseRepo: SupabaseRepo,
    private val syncRequest: SyncRequestRepo,
    private val dataStoreRepo: DataStoreRepo
) : TodoRepo {
    override fun readAllDataFlow(): Flow<List<Todo>> =
        todoDao.readAllDataFlow().map {
            it.map { data ->
                Log.e("TAG", "readAllDataFlow: $it", )
                data.toTodo()
            }
        }

    override fun readSelectedData(date: String): Flow<List<Todo>> =
        todoDao.readSelectedDataFlow(date).map {
            it.map { data -> data.toTodo() }
        }

    override suspend fun readToUpdateData(date: String): List<TodoEntity> =
        todoDao.readToUpdateData(date)


    override suspend fun insertData(list: List<Todo>) {
        val todoEntityList = list.map {
            TodoEntity(
                supaId = 0,
                updateDateTime = LocalDate.now().toString(),
                isSynced = false,
                isDeleted = false,
                title = it.title,
                content = it.content,
                date = it.date,
                isDone = it.isDone
            )
        }
        val result = todoDao.insertData(todoEntityList)
        Log.e("TAG", "insertData: $result")
        syncRequest.syncRequest()

        Log.e("TAG", "sync 완료 완료")
    }

    override suspend fun syncUpdateData(): Boolean {
        val insertList = mutableListOf<TodoEntity>()

        val supaList = supabaseRepo.readUpdatedTodoData(dataStoreRepo.getLastUpdateDateTime())
        Log.e("TAG", "syncUpdateData supaList : $supaList", )

        val localList = todoDao.readAllDataFlow().first()
        Log.e("TAG", "syncUpdateData localList : $localList", )

        val supaIdList = supaList.map { it.id }
        Log.e("TAG", "syncUpdateData supaIdList : $supaIdList", )

        // [1]
        val existLocalList = localList.filter { supaIdList.contains(it.supaId) }
        val existLocalListId = existLocalList.map { it.supaId }
        Log.e("TAG", "syncUpdateData existLocalDataListId : $existLocalList", )

        // Supa, Local 에 존재 - 수정, 삭제
        existLocalList.forEach { local ->
            insertList.add(supaList.first { supa -> supa.id == local.supaId }.toIdTodoEntity(local.id))
        }
        // Supa 에만 존재 - 추가
        supaList.filter { !existLocalListId.contains(it.id) }.forEach {
            insertList.add(it.todoEntity())
        }
        Log.e("TAG", "syncUpdateData insertList : $insertList", )
        todoDao.insertData(insertList)
        return true
    }




    override suspend fun insertTodoEntitySyncData(list: List<TodoEntity>) {
        val result = todoDao.insertData(list)
        Log.e("TAG", "insertTodoEntitySyncData: $result")
    }


    override suspend fun deleteData(id: List<Long>) {
        val result = todoDao.deleteData(id)
        Log.e("TAG", "deleteData: $result")
        syncRequest.syncRequest()
    }

    override suspend fun deleteSyncData(id: List<Long>) {
        val result = todoDao.deleteData(id)
        Log.e("TAG", "deleteSyncData: $result")
    }


    override suspend fun changeCheckBox(id: Long, status: Boolean) {
        todoDao.changeCheckBox(id, status)
        syncRequest.syncRequest()
    }


    override suspend fun syncWith(typeData: RequestType): Boolean {
        return true
    }
}

