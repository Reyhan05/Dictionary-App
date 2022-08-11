package com.reyhan.dictionaryenglish.network

import com.reyhan.dictionaryenglish.data.DictionaryResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("en/{word}")
    fun getSearch(
        @Path("word") word: String
    ): Call<List<DictionaryResponseItem>>
}