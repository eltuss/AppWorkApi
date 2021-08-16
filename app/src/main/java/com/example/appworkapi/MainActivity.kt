package com.example.appworkapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appworkapi.api.MyRetrofit
import com.example.appworkapi.model.Product
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    lateinit var recycleProdusts: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleProdusts = findViewById(R.id.recycler_products)
        recycleProdusts.layoutManager = LinearLayoutManager(this)
        getData()
    }

    private fun getData(){
        val call: retrofit2.Call<List<Product>> =
            MyRetrofit.instance?.productApi()?.getProductApi() as retrofit2.Call<List<Product>>
        call.enqueue(object : retrofit2.Callback<List<Product>>{
            override fun onResponse(call: retrofit2.Call<List<Product>>, response: Response<List<Product>>) {
                val adapter = response.body()?.let { ProductAdapter(this@MainActivity, it.toList()) }
                recycleProdusts.adapter = adapter
            }

            override fun onFailure(call: retrofit2.Call<List<Product>>, t: Throwable) {
               Toast.makeText(this@MainActivity,t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}