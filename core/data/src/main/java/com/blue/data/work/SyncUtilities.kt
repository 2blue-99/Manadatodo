package com.blue.data.work

import com.blue.data.work.status.RequestType

interface Syncable{
    suspend fun syncWith(typeData: RequestType): Boolean
}