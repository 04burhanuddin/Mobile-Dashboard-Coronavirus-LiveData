package com.tugasakhirsemester.burhanuddin.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    val instance:ApiEndpoint by lazy {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.kawalcorona.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        retrofit.create(ApiEndpoint::class.java)
    }
}