package com.retrofit.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityDetailBinding
import com.retrofit.adapter.MainAdapter
import com.retrofit.network.RetrofitService
import com.retrofit.repository.MainRepository

import com.retrofit.viewmodel.MainViewModel
import com.retrofit.viewmodel.MyViewModelFactory

class Detail  : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    private val createService = RetrofitService.postInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(MainRepository(retrofitService, createService))
        ).get(
            MainViewModel::class.java
        )
        viewModel.getAllMakes()
        viewModel.makeList.observe(this, {
            it?.let{ data->
                println(">> test"+data.listings.size)

                val movie = data.listings.get(intent.getIntExtra("pos",0))
                Glide.with(this).load( movie.images.firstPhoto.medium).into(binding.image);
                binding.name.setText(movie.year + "  " + movie.make+ "  " +movie.model+ "  " +movie.trim)
                binding.milage.text = movie.listPrice.toString() + "  " + movie.mileage
                binding.vdetail.text = movie.dealer.city + "\n" + movie.exteriorColor+"\n"+
                                       movie.interiorColor+"\n"+movie.drivetype+"\n"+
                                       movie.transmission+"\n"+movie.engine+"\n"+movie.fuel
            }
        })
       // binding.recyclerview.adapter = adapter







    }
}