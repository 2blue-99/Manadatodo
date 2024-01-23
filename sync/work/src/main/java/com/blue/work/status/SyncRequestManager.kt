package com.blue.work.status

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SyncRequestManager @Inject constructor(
    @ApplicationContext private val context: Context
)  {
    fun syncRequest(number: Int){
        when(number){
            1 -> {}
            2 -> {}
            3 -> {}
            4 -> {}
            else -> {}
        }
    }
}