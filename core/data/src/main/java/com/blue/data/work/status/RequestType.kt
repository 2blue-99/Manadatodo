package com.blue.data.work.status

import com.blue.model.Mandalart
import com.blue.model.Todo

sealed interface RequestType{
    enum class TypeName{ InsertTodo,DeleteTodo,InsertMandalart, DeleteMandalart }
    data class InsertTodo(val todo: Todo): RequestType { val insertTodoName = "InsertTodo" }
    data class DeleteTodo(val id: Int): RequestType
    data class InsertMandalart(val Mandalart: Mandalart): RequestType
    data class DeleteMandalart(val id: Int): RequestType
}
