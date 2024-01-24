package com.blue.supabase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.blue.model.Mandalart

@Entity(tableName = "Mandalart")
data class MandalartEntity(
    @PrimaryKey var id: Int,
    @ColumnInfo var cnt: Int
)

fun MandalartEntity.toMandalart(): Mandalart = Mandalart(id, cnt)
