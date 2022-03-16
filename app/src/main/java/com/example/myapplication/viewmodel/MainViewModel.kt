package com.retrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.retrofit.model.MakeModel
import com.retrofit.network.RetrofitService
import com.retrofit.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    val makeList = MutableLiveData<MakeModel>()
    val postString = MutableLiveData<String>()
    val errorMessage = MutableLiveData<String>()
    fun getAllMakes() {
        val response = repository.getMakes()
        response.enqueue(object : Callback<MakeModel> {
            override fun onResponse(call: Call<MakeModel>, response: Response<MakeModel>) {
                makeList.postValue(response.body())
            }
            override fun onFailure(call: Call<MakeModel>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
    fun createEmployee()
    {
        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://dummy.restapiexample.com")
            .build()

        // Create Service
        val repository = retrofit.create(RetrofitService::class.java)

        // Create JSON using JSONObject
        val jsonObject = JSONObject()
        jsonObject.put("name", "Balwinder Singh")
        jsonObject.put("salary", "100000")
        jsonObject.put("age", "32")

        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()

        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            // Do the POST request and get response
            val response=repository.createEmployee(requestBody)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful)
                {
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(JsonParser.parseString(response.body()?.string()))
                    postString.value=prettyJson
                }
                else
                {
                    postString.value="Server Down"
                }
            }
        }

    }
}