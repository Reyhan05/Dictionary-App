package com.reyhan.dictionaryenglish.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reyhan.dictionaryenglish.data.DictionaryResponseItem
import com.reyhan.dictionaryenglish.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DictionaryViewModel: ViewModel() {

    val dictionary = MutableLiveData<List<DictionaryResponseItem>>()

    fun getDataApi(query: String?){
        if (query != null) {
            ApiConfig.getApiService().getSearch(query).enqueue(object : Callback<List<DictionaryResponseItem>> {
                override fun onResponse(
                    call: Call<List<DictionaryResponseItem>>,
                    response: Response<List<DictionaryResponseItem>>
                ) {
                    dictionary.value = response.body()
                }

                override fun onFailure(call: Call<List<DictionaryResponseItem>>, t: Throwable) {
                    Log.e("TAG", "onFailure: $t")
                }

            })
        }
    }
}