package com.tugasakhirsemester.burhanuddin.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.tugasakhirsemester.burhanuddin.R
import com.tugasakhirsemester.burhanuddin.databinding.ActivityGlobalDataBinding
import com.tugasakhirsemester.burhanuddin.datas.GlobalDeathResponse
import com.tugasakhirsemester.burhanuddin.datas.GlobalPositifResponse
import com.tugasakhirsemester.burhanuddin.datas.GlobalRecoverResponse
import com.tugasakhirsemester.burhanuddin.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GlobalDataActivity : AppCompatActivity(), View.OnClickListener{

    private val BindingGlobal by lazy { ActivityGlobalDataBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(BindingGlobal.root)

        GlobalPositifResponse()
        GlobalRecoverResponse()
        GlobalDeathResponse()

        BindingGlobal.goBeranda.setOnClickListener(this)
        BindingGlobal.maps.setOnClickListener(this)
        BindingGlobal.googleBerita.setOnClickListener(this)
    }

    override fun onClick(Click: View) {
        when(Click.id){
            R.id.goBeranda ->{
                val Kembali = Intent(this@GlobalDataActivity, IndonesiaDataActivity::class.java)
                startActivity(Kembali)
            }
            R.id.maps-> {
                val Kemaps = Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/covid19/map?hl=id&gl=ID&ceid=ID%3Aid"))
                startActivity(Kemaps)
            }
            R.id.google_berita-> {
                val KeGoogleBerita = Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/covid19/map?hl=id&gl=ID&ceid=ID%3Aid"))
                startActivity(KeGoogleBerita)
            }
        }
    }

    private fun GlobalPositifResponse(){
        ApiService.instance.getPositif().enqueue(object : Callback<GlobalPositifResponse>{
            override fun onResponse(
                call: Call<GlobalPositifResponse>, response: Response<GlobalPositifResponse>)
            {
                val TV_GlobalPositifResponse = response.body()
                val TotalPositif = TV_GlobalPositifResponse?.value

                BindingGlobal.GLPositif.text = TotalPositif
            }
            override fun onFailure(call: Call<GlobalPositifResponse>, t: Throwable) {
                Toast.makeText(this@GlobalDataActivity,"Check your internet connection and try again to connect", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun GlobalRecoverResponse(){
        ApiService.instance.getSembuh().enqueue(object : Callback<GlobalRecoverResponse>{
            override fun onResponse(
                call: Call<GlobalRecoverResponse>, response: Response<GlobalRecoverResponse>)
            {
                val TV_GlobalRecoverResponse = response.body()
                val TotalSembuh = TV_GlobalRecoverResponse?.value

                BindingGlobal.GLRecover.text = TotalSembuh
            }

            override fun onFailure(call: Call<GlobalRecoverResponse>, t: Throwable) {
                Toast.makeText(this@GlobalDataActivity,"Check your internet connection and try again to connect", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun GlobalDeathResponse(){
        ApiService.instance.getMeninggal().enqueue(object : Callback<GlobalDeathResponse>{
            override fun onResponse(
                call: Call<GlobalDeathResponse>, response: Response<GlobalDeathResponse>)
            {
                val TV_GlobalDeathResponse = response.body()
                val TotalMeninggal = TV_GlobalDeathResponse?.value

                BindingGlobal.GLDeath.text = TotalMeninggal
            }
            override fun onFailure(call: Call<GlobalDeathResponse>, t: Throwable) {
                Toast.makeText(this@GlobalDataActivity,"Check your internet connection and try again to connect", Toast.LENGTH_SHORT).show()
            }
        })
    }
}