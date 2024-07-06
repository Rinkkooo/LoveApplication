package com.example.loveapplication.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {
    @Insert
    fun insert(historyEntity: HistoryEntity)

    @Query("SELECT * FROM history_table ORDER BY firstname ASC")
    fun getAll(): LiveData<List<HistoryEntity>>

    @Delete
    fun delete(historyEntity: HistoryEntity)
}