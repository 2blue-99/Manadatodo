package com.blue.history

import android.icu.text.SimpleDateFormat
import java.util.Date

object Util {
    fun convertMillisToDate(millis: Long): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        return formatter.format(Date(millis))
    }
}