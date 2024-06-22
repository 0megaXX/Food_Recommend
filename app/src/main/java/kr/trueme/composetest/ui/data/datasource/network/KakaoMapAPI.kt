package kr.trueme.composetest.ui.data.datasource.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface KakaoMapAPI {


    @GET("main/v/{id}")
    fun getPhotos(
        @Path("id") id: String,
    ): Call<Map<String, Any>>

}