package com.tugasakhirsemester.burhanuddin.retrofit

import com.tugasakhirsemester.burhanuddin.datas.GlobalDeathResponse
import com.tugasakhirsemester.burhanuddin.datas.GlobalPositifResponse
import com.tugasakhirsemester.burhanuddin.datas.GlobalRecoverResponse
import com.tugasakhirsemester.burhanuddin.datas.DataIndonesiaResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndpoint {
    @GET("indonesia")
    fun getDataIndonesia(): Call<ArrayList<DataIndonesiaResponse>>

    @GET("positif")
    fun getPositif(): Call<GlobalPositifResponse>

    @GET("sembuh")
    fun getSembuh(): Call<GlobalRecoverResponse>

    @GET("meninggal")
    fun getMeninggal(): Call<GlobalDeathResponse>
}