package com.blue.data.repo.database

import android.util.Log
import com.blue.data.repo.datastore.DataStoreRepo
import com.blue.data.work.status.RequestType
import com.blue.data.work.status.SyncRequestRepo
import com.blue.database.local.dao.TodoDao
import com.blue.database.local.model.TodoEntity
import com.blue.database.local.model.toTodo
import com.blue.model.Todo
import com.blue.supabase.model.TodoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class TodoRepoImpl @Inject constructor(
    private val todoDao: TodoDao,
    private val syncRequest: SyncRequestRepo,
    private val dataStoreRepo: DataStoreRepo
) : TodoRepo {
    override fun readAllDataFlow(): Flow<List<TodoEntity>> =
        todoDao.readAllDataFlow()
    override fun readSelectedData(date: String): Flow<List<Todo>> =
        todoDao.readSelectedDataFlow(date).map {
            it.map { data -> data.toTodo() }
        }
    override suspend fun readToUpdateData(): List<TodoEntity> =
        todoDao.readToUpdateData(dataStoreRepo.getLastUpdateDateTime())



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
    override suspend fun insertTodoModelSyncData(list: List<TodoModel>) {
        val todoEntityList = list.map {
            TodoEntity(
                supaId = it.id,
                updateDateTime = it.update_date_time,
                isSynced = true,
                isDeleted = false,
                title = it.title,
                content = it.content,
                date = it.date,
                isDone = it.isDone
            )
        }
        val result = todoDao.insertData(todoEntityList)
        Log.e("TAG", "insertTodoModelSyncData: $result", )
    }

    override suspend fun insertTodoEntitySyncData(list: List<TodoEntity>) {
        val result = todoDao.insertData(list)
        Log.e("TAG", "insertTodoEntitySyncData: $result", )
    }


    override suspend fun deleteData(id: List<Long>) {
        val result = todoDao.deleteData(id)
        Log.e("TAG", "deleteData: $result", )
        syncRequest.syncRequest()
    }
    override suspend fun deleteSyncData(id: List<Long>) {
        val result = todoDao.deleteData(id)
        Log.e("TAG", "deleteSyncData: $result", )
    }



    override suspend fun changeCheckBox(id: Long, status: Boolean) {
        todoDao.changeCheckBox(id, status)
        syncRequest.syncRequest()
    }



    override suspend fun syncWith(typeData: RequestType): Boolean {
        return true
    }
}
