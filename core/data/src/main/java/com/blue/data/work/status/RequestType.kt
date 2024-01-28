package com.blue.data.work.status

import com.blue.model.Mandalart
import com.blue.model.Todo

sealed interface RequestType{
    enum class Name{ Sync, Write }
    data object Sync: RequestType
    data object Write: RequestType
}

//sealed interface RequestType{
//    enum class TypeName{ InsertTodo,DeleteTodo,InsertMandalart, DeleteMandalart }
//    data class InsertTodo(val todo: Todo): RequestType
//    data class DeleteTodo(val id: Long): RequestType
//    data class InsertMandalart(val Mandalart: Mandalart): RequestType
//    data class DeleteMandalart(val id: Long): RequestType
//}

