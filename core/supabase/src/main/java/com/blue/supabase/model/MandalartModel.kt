package com.blue.supabase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.blue.model.Mandalart
import kotlinx.serialization.Serializable

@Serializable
data class MandalartModel(
    @PrimaryKey var id: Int,
    @ColumnInfo var cnt: Int
)

fun MandalartModel.toMandalart(): Mandalart = Mandalart(id, cnt)
