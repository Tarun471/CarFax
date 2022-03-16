package com.retrofit.network


import com.retrofit.model.MakeModel
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {

    @GET("assignment.json")
    fun getAllMakes(): Call<MakeModel>



    @POST("/api/v1/create")
    suspend fun createEmployee(@Body requestBody: RequestBody): Response<ResponseBody>

    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://carfax-for-consumers.firebaseio.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)

            return retrofitService!!
        }

        fun postInstance():RetrofitService
        {
            val client = OkHttpClient.Builder().build()
            val retrofit=Retrofit.Builder()
                .baseUrl("http://dummy.restapiexample.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            retrofitService = retrofit.create(RetrofitService::class.java)
            return retrofitService!!

        }
    }

}