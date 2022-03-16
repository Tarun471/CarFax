package com.retrofit.network

object RequestBodies {

    data class EmployeeDetails(
        val name:String,
        val salery:String,
        val age:Int
    )

}