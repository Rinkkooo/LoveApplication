package com.example.loveapplication.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class HistoryEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var firstName: String,
    var secondName: String,
    val result: String,
    var percentage: String,
)