package com.tugasakhirsemester.burhanuddin.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    /*
    * kita akan membuat variabel yang tidak bisa di ganti dan non-null type
    * maka kit amenggunakan property lazy yang berguna untuk membuat
    * type non-null tapi read only maka tidak bisa di ubah lagi
    */
    val instance:ApiEndpoint by lazy {
        val retrofit = Retrofit.Builder()
                //Url dari API
                .baseUrl("https://api.kawalcorona.com/")
                //converter json dari retrofit
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        retrofit.create(ApiEndpoint::class.java)
    }
}