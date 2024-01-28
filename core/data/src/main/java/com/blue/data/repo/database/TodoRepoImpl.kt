package com.blue.data.repo.database

import android.util.Log
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
) : TodoRepo {
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

    override suspend fun insertSyncData(list: List<TodoModel>) {
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
        Log.e("TAG", "insertSyncData: $result", )
    }

    override fun readAllDataFlow(): Flow<List<TodoEntity>> =
        todoDao.readAllDataFlow()

    override suspend fun deleteData(id: Long) {
        todoDao.deleteData(id)
        syncRequest.syncRequest()
    }

    override suspend fun changeCheckBox(id: Long, status: Boolean) {
        todoDao.changeCheckBox(id, status)
        syncRequest.syncRequest()
    }

    override fun readSelectedDataFlow(date: String): Flow<List<Todo>> =
        todoDao.readSelectedDataFlow(date).map {
            it.map { data -> data.toTodo() }
        }

    override fun readToUpdateData(date: String): List<Todo> {
        return todoDao.readToUpdateData(date).map { it.toTodo() }
    }

    override suspend fun syncWith(typeData: RequestType): Boolean {
        return true
    }

}
