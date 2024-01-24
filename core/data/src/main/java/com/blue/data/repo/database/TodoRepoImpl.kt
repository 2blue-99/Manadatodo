package com.blue.data.repo.database

import com.blue.data.repo.supabase.SupabaseRepo
import com.blue.data.work.status.RequestType
import com.blue.data.work.status.SyncRequestInterface
import com.blue.database.local.dao.TodoDao
import com.blue.database.local.model.TodoEntity
import com.blue.database.local.model.todoEntityToTodo
import com.blue.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class TodoRepoImpl @Inject constructor(
    private val todoDao: TodoDao,
    private val syncRequest: SyncRequestInterface,
    private val supabaseRepo: SupabaseRepo
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
        syncRequest.syncRequest(RequestType.InsertTodo(data))
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

    override suspend fun syncWith(typeData: RequestType): Boolean {
        return true
    }

//    override suspend fun syncWith(data: RequestType): Boolean {
//        todoDao.insertData(
//            TodoEntity(
//                id = 0,
//                date = LocalDate.now().toString(),
//                title = "네트워크 연결",
//                content = "네트워크가 연결되었습니다요",
//                isDone = false
//            )
//        )
//        return true
//    }
}
