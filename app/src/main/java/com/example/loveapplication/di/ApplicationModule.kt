package com.example.loveapplication.di

import android.app.AlertDialog
import android.content.Context
import androidx.room.Room
import com.example.loveapplication.data.local.AppDatabase
import com.example.loveapplication.data.local.HistoryDao
import com.example.loveapplication.data.local.HistoryEntity
import com.example.loveapplication.data.network.ApiService
import com.example.loveapplication.interfaces.OnClick
import com.example.loveapplication.ui.fragments.history_fragment.HistoryAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase{
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideHistoryDao(database: AppDatabase): HistoryDao {
        return database.historyDao()
    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService{
        return Retrofit.Builder()
            .baseUrl("https://love-calculator.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOnClick(@ApplicationContext context: Context, historyDao: HistoryDao): OnClick {
        return object : OnClick {
            override fun onLongClick(historyEntity: HistoryEntity) {
                val builder = AlertDialog.Builder(context)
                with(builder) {
                    setTitle("Do you want to delete it?")
                    setPositiveButton("Yes") { dialog, which ->
                        historyDao.delete(historyEntity)
                    }
                    setNegativeButton("Нет") { dialog, which ->
                        dialog.cancel()
                    }
                    show()
                }
                builder.create()
            }
        }
    }

    @Provides
    fun provideHistoryAdapter(onClick: OnClick): HistoryAdapter {
        return HistoryAdapter(onClick)
    }
}