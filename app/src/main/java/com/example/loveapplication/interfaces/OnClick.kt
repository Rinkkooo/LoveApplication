package com.example.loveapplication.interfaces

import com.example.loveapplication.data.local.HistoryEntity

interface OnClick {
    fun onLongClick(historyEntity: HistoryEntity)
}