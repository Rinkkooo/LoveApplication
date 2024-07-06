package com.example.loveapplication.ui.fragments.calculate_fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loveapplication.data.local.HistoryDao
import com.example.loveapplication.data.local.HistoryEntity
import com.example.loveapplication.data.network.ApiService
import com.example.loveapplication.data.network.LoveResult
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CalculateViewModel @Inject constructor(
    private val api: ApiService,
    private val historyDao: HistoryDao
) : ViewModel() {

    val loveResultData = MutableLiveData<LoveResult>()
    val errorMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    fun getPercentage(firstName: String, secondName: String) {
        isLoading.postValue(true)
        api.getPercentage(
            apiKey = "13db8c0c9fmsh0e8b65404615b3ap1035a5jsn85bfe5faab5c",
            host = "love-calculator.p.rapidapi.com",
            firstName = firstName,
            secondName = secondName,
        ).enqueue(object : Callback<LoveResult> {

            override fun onResponse(call: Call<LoveResult>, response: Response<LoveResult>) {
                isLoading.postValue(false)
                if (response.isSuccessful && response.body() != null) {
                    val loveResult = response.body()!!
                    saveToHistory(firstName, secondName, loveResult)
                    loveResultData.postValue(loveResult)
                } else {
                    errorMessage.postValue("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<LoveResult>, t: Throwable) {
                isLoading.postValue(false)
                errorMessage.postValue("Error: ${t.message}")
            }
        })
    }

    fun saveToHistory(firstName: String, secondName: String, loveResult: LoveResult) {
        isLoading.postValue(true)
            val historyEntity = HistoryEntity(
                firstName = firstName,
                secondName = secondName,
                result = loveResult.result,
                percentage = loveResult.percentage
            )
        historyDao.insert(historyEntity)
        isLoading.postValue(false)
    }
}