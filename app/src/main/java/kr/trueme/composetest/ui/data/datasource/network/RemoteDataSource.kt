package kr.trueme.composetest.ui.data.datasource.network

import android.util.Log
import androidx.compose.runtime.MutableState
import kr.trueme.composetest.ui.data.model.AddressDocument
import kr.trueme.composetest.ui.data.model.Document
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG: String = "REMOTE DATA SOURCE"
private val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://dapi.kakao.com/")
    .addConverterFactory(GsonConverterFactory.create()).build()
private val retrofitAPI: KakaoAPI = retrofit.create(KakaoAPI::class.java)

fun searchMap(
    query: String = "한식",
    documents: MutableState<List<Document>>,
) {
    val call: Call<Map<String, Any>> = retrofitAPI.getSearchKeyword(
        query = query,
        token = RemoteDataSourceKey.KakaoAPIKey,
        x = "127.06283102249932",
        y = "37.514322572335935",
        radius = "20000"
    )

    call.enqueue(object : Callback<Map<String, Any>?> {
        override fun onResponse(
            call: Call<Map<String, Any>?>,
            response: Response<Map<String, Any>?>,
        ) {
            Log.d(TAG, "onResponse 성공")
            if (response.isSuccessful) {
                val responseBody = response.body()!!
                val results = responseBody["documents"] as List<*>
                val docs = mutableListOf<Document>()
                for (result in results) {
                    val castedDoc = Document.fromJson(json = result as Map<String, Any>)
                    docs.add(castedDoc)
                }
                documents.value = docs
            }
        }

        override fun onFailure(call: Call<Map<String, Any>?>, t: Throwable) {
            Log.d(TAG, "onFailure 실패")
        }
    })
}

fun searchMap(
    query: String = "한식",
    x: Double,
    y: Double,
    radius: Int,
    documents: MutableState<List<Document>>,
) {
    val call: Call<Map<String, Any>> = retrofitAPI.getSearchKeyword(
        query = query,
        token = RemoteDataSourceKey.KakaoAPIKey,
        x = x.toString(),
        y = y.toString(),
        radius = radius.toString()
    )

    call.enqueue(object : Callback<Map<String, Any>?> {
        override fun onResponse(
            call: Call<Map<String, Any>?>,
            response: Response<Map<String, Any>?>,
        ) {
            Log.d(TAG, "onResponse 성공")
            if (response.isSuccessful) {
                val responseBody = response.body()!!
                val results = responseBody["documents"] as List<*>
                val docs = mutableListOf<Document>()
                for (result in results) {
                    val castedDoc = Document.fromJson(json = result as Map<String, Any>)
                    docs.add(castedDoc)
                }
                documents.value = docs
            }
        }

        override fun onFailure(call: Call<Map<String, Any>?>, t: Throwable) {
            Log.d(TAG, "onFailure 실패")
        }
    })
}

fun searchAddress(
    query: String = "전북 삼성동 100",
    documents: MutableState<List<AddressDocument>>,
) {
    val call: Call<Map<String, Any>> = retrofitAPI.getSearchAddress(
        token = RemoteDataSourceKey.KakaoAPIKey,
        query = query,
    )

    call.enqueue(object : Callback<Map<String, Any>?> {
        override fun onResponse(
            call: Call<Map<String, Any>?>,
            response: Response<Map<String, Any>?>,
        ) {
            Log.d(TAG, "onResponse 성공")
            if (response.isSuccessful) {
                val responseBody = response.body()!!
                val results = responseBody["documents"] as List<*>
                val docs = mutableListOf<AddressDocument>()
                for (result in results) {
                    val castedDoc = AddressDocument.fromJson(json = result as Map<String, Any>)
                    Log.d("aaa", castedDoc.toString())
                    docs.add(castedDoc)
                }
                documents.value = docs
            }
        }

        override fun onFailure(call: Call<Map<String, Any>?>, t: Throwable) {
            Log.d(TAG, "onFailure 실패")
        }
    })
}