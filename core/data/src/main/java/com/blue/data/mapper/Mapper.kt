package com.blue.data.mapper

import com.blue.database.local.model.TodoEntity
import com.blue.supabase.model.TodoModel

object Mapper {

    fun TodoModel.asTodoEntity(localId: Long): TodoEntity =
        TodoEntity(
            id = localId,
            supaId = id,
            updateDateTime = update_date_time,
            isSynced = true,
            isDeleted = is_deleted,
            title = title,
            content = content,
            date = date,
            isDone = isDone
        )
    fun TodoModel.asTodoEntity(): TodoEntity =
        TodoEntity(
            supaId = id,
            updateDateTime = update_date_time,
            isSynced = true,
            isDeleted = is_deleted,
            title = title,
            content = content,
            date = date,
            isDone = isDone
        )




    fun TodoEntity.asTodoModel(isDeleted: Boolean): TodoModel =
        TodoModel(
            id = supaId,
            date = date,
            title = title,
            content = content,
            isDone = isDone,
            update_date_time = updateDateTime,
            is_deleted = isDeleted,
        )
    fun TodoEntity.asTodoModel(): TodoModel =
        TodoModel(
            id = supaId,
            date = date,
            title = title,
            content = content,
            isDone = isDone,
            update_date_time = updateDateTime,
            is_deleted = isDeleted,
        )
}