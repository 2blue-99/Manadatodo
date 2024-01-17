package com.blue.history

import android.icu.text.SimpleDateFormat
import android.util.Log
import java.util.Date

object Util {
    fun convertMillisToDate(millis: Long): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        return formatter.format(Date(millis))
    }
}