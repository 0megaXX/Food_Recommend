package kr.trueme.composetest.ui.data.datasource.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoAPI {

    @GET("v2/local/search/keyword.json")
    fun getSearchKeyword(
        @Header("Authorization") token: String,
        @Query("query", encoded = true) query: String,
        @Query("x") x: String,
        @Query("y") y: String,
        @Query("radius") radius: String,
    ): Call<Map<String, Any>>

    @GET("v2/local/search/address.json")
    fun getSearchAddress(
        @Header("Authorization") token: String,
        @Query("query", encoded = true) query: String,
    ): Call<Map<String, Any>>

}