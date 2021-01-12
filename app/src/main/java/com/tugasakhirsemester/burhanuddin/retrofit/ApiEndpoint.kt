package com.tugasakhirsemester.burhanuddin.retrofit

import com.tugasakhirsemester.burhanuddin.model.GlobalDeathResponse
import com.tugasakhirsemester.burhanuddin.model.GlobalPositifResponse
import com.tugasakhirsemester.burhanuddin.model.GlobalRecoverResponse
import com.tugasakhirsemester.burhanuddin.model.DataIndonesiaResponse
import retrofit2.Call
import retrofit2.http.GET

/*
* Karan kita menggunakan retrofit untuk mengatur request ke server
* maka get yang kita gunakan adalah dari retrofit
*/

interface ApiEndpoint {
    //Get data indonesia
    @GET("indonesia")
    fun getDataIndonesia(): Call<ArrayList<DataIndonesiaResponse>>

    //Get global data positif
    @GET("positif")
    fun getPositif(): Call<GlobalPositifResponse>

    //Get global data sembuh
    @GET("sembuh")
    fun getSembuh(): Call<GlobalRecoverResponse>

    //Get global data meninggal
    @GET("meninggal")
    fun getMeninggal(): Call<GlobalDeathResponse>
}