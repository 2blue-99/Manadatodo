package com.blue.work.status

import com.blue.data.RequestType

interface SyncRequestInterface {
    fun syncRequest(requestType: RequestType)
}