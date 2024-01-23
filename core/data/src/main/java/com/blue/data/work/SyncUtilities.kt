package com.blue.data.work

import com.blue.data.work.status.RequestType


//interface Synchronizer{
//    suspend fun Syncable.sync() = this@sync.syncWith(this@Synchronizer)
//}

interface Syncable{
    suspend fun syncWith(typeData: RequestType): Boolean
}