package com.retrofit.repository

import com.retrofit.network.RequestBodies
import com.retrofit.network.RetrofitService

class MainRepository(private val retrofitService: RetrofitService,private val createService: RetrofitService) {

    fun getMakes() = retrofitService.getAllMakes()

  /*  fun createEmp(body: RequestBodies.EmployeeDetails) =createService.createEmployee(body)*/

}