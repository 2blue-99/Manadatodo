package com.blue.data.repo.database

import com.blue.database.dao.TodoDao
import com.blue.database.model.TodoEntity
import com.blue.database.model.todoEntityToTodo
import com.blue.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoRepoImpl @Inject constructor(
    private val todoDao: TodoDao
) : TodoRepo {
    override suspend fun insertData(data: Todo) {
        todoDao.insertData(
            TodoEntity(
                id = data.id,
                date = data.date,
                title = data.title,
                content = data.content,
                isDone = data.isDone
            )
        )
    }

    override fun readAllData(): Flow<List<Todo>> =
        todoDao.readAllData().map {
            it.map { data -> data.todoEntityToTodo() }
        }

    override suspend fun deleteData(id: Int) =
        todoDao.deleteData(id)

    override suspend fun changeCheckBox(id: Int, status: Boolean) {
        todoDao.changeCheckBox(id, status)
    }

    override fun readSelectedData(date: String): Flow<List<Todo>> =
        todoDao.readSelectedData(date).map {
            it.map { data -> data.todoEntityToTodo() }
        }

}
