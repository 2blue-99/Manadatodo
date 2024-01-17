package com.blue.data.repo

import com.blue.database.dao.TodoDao
import com.blue.database.model.TodoEntity
import com.blue.database.model.todoEntityToTodo
import com.blue.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DatabaseRepoImpl @Inject constructor(
    private val dataBase: TodoDao
) : DatabaseRepo {
    override suspend fun insertData(data: Todo) {
        dataBase.insertData(
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
        dataBase.readAllData().map {
            it.map { data -> data.todoEntityToTodo() }
        }

    override suspend fun deleteData(id: Int) =
        dataBase.deleteData(id)

    override suspend fun changeCheckBox(id: Int, status: Boolean) {
        dataBase.changeCheckBox(id, status)
    }

    override fun readSelectedData(date: String): Flow<List<Todo>> =
        dataBase.readSelectedData(date).map {
            it.map { data -> data.todoEntityToTodo() }
        }

}
