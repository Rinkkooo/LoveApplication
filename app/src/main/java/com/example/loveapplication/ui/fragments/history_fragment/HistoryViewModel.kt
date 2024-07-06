package com.example.loveapplication.ui.fragments.history_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loveapplication.data.local.HistoryDao
import com.example.loveapplication.data.local.HistoryEntity
import com.example.loveapplication.data.network.LoveResult
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyDao: HistoryDao
): ViewModel() {

    fun getHistory() = historyDao.getAll()

    fun delete(historyEntity: HistoryEntity) {
        viewModelScope.launch {
            historyDao.delete(historyEntity)
        }
    }
}