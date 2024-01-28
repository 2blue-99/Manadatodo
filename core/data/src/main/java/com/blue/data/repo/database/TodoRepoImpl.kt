package com.blue.data.repo.database

import com.blue.data.work.status.RequestType
import com.blue.data.work.status.SyncRequestRepo
import com.blue.database.local.dao.TodoDao
import com.blue.database.local.model.TodoEntity
import com.blue.database.local.model.toTodoModel
import com.blue.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoRepoImpl @Inject constructor(
    private val todoDao: TodoDao,
    private val syncRequest: SyncRequestRepo,
) : TodoRepo {
    override suspend fun insertData(data: Todo) {
        todoDao.insertData(
            TodoEntity(
                id = data.id,
                updateDateTime = data.date,
                title = data.title,
                content = data.content,
                isDone = data.isDone
            )
        )
        syncRequest.syncRequest()
    }

    override fun readAllDataFlow(): Flow<List<Todo>> =
        todoDao.readAllDataFlow().map {
            it.map { data -> data.toTodoModel() }
        }

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
            it.map { data -> data.toTodoModel() }
        }

    override fun readToUpdateData(date: String): List<Todo> {
        return todoDao.readToUpdateData(date).map { it.toTodoModel() }
    }

    override suspend fun syncWith(typeData: RequestType): Boolean {
        return true
    }

}
