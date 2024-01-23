package com.blue.data.work.status

import com.blue.data.work.status.RequestType

interface SyncRequestInterface {
    fun syncRequest(requestType: RequestType)
}