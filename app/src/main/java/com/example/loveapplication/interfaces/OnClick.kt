package com.example.loveapplication.interfaces

import android.content.Context
import com.example.loveapplication.data.local.HistoryEntity

interface OnClick {
    fun onLongClick(context: Context, historyEntity: HistoryEntity)
}