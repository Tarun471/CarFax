package com.retrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.retrofit.adapter.MainAdapter

import com.retrofit.network.RetrofitService
import com.retrofit.repository.MainRepository

import com.retrofit.viewmodel.MainViewModel
import com.retrofit.viewmodel.MyViewModelFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    private val createService = RetrofitService.postInstance()
    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(MainRepository(retrofitService, createService))
        ).get(
            MainViewModel::class.java
        )

        binding.recyclerview.adapter = adapter

        viewModel.makeList.observe(this, {
            it?.let{ data->
                println(">> test"+data.listings.size)
                adapter.setMovieList(data.listings,this)
            // Toast.makeText(this,data.listings.get(0).images.firstPhoto.large, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.postString.observe(this, {
              binding.jsonResultsTextview.text=it
        })
        viewModel.errorMessage.observe(this, {

        })
        viewModel.getAllMakes()


    }


}