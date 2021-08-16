package com.example.appworkapi.api

import android.telecom.Call
import com.example.appworkapi.model.Product
import retrofit2.http.GET

interface ProductApi {
    @GET("getdata.php")
    fun getProductApi(): retrofit2.Call<List<Product>>
}