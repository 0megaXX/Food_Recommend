package kr.trueme.composetest.ui.data.datasource.network

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG: String = "REMOTE DATA SOURCE"
private const val BASE_URL = "https://place.map.kakao.com/"
private val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create()).build()

private val retrofitAPI: KakaoMapAPI = retrofit.create(KakaoMapAPI::class.java)

fun searchPhotos(
    id: String,
) {
    val call: Call<Map<String, Any>> = retrofitAPI.getPhotos(id)

    call.enqueue(object : Callback<Map<String, Any>?> {
        override fun onResponse(
            call: Call<Map<String, Any>?>,
            response: Response<Map<String, Any>?>,
        ) {
            Log.d(TAG, "onResponse 성공")
            if (response.isSuccessful) {
                val responseBody = response.body()!!
                responseBody.toMutableMap().apply {
                    Log.d("aaa", get("photo").toString())
                }
            }
        }

        override fun onFailure(call: Call<Map<String, Any>?>, t: Throwable) {
            Log.d(TAG, "onFailure 실패")
        }
    })
}