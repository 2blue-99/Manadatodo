package com.blue.data.mapper

import com.blue.database.local.model.TodoEntity
import com.blue.supabase.model.TodoModel

object Mapper {
    fun TodoModel.toIdTodoEntity(id: Long): TodoEntity =
        TodoEntity(
            id = id,
            supaId = this.id,
            updateDateTime = this.update_date_time,
            isSynced = true,
            isDeleted = this.is_deleted,
            title = this.title,
            content = this.content,
            date = this.date,
            isDone = this.isDone
        )

    fun TodoModel.todoEntity(): TodoEntity =
        TodoEntity(
            id = 0,
            supaId = this.id,
            updateDateTime = this.update_date_time,
            isSynced = true,
            isDeleted = this.is_deleted,
            title = this.title,
            content = this.content,
            date = this.date,
            isDone = this.isDone
        )
}