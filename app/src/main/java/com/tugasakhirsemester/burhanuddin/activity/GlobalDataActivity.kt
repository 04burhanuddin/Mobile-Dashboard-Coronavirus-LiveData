package com.tugasakhirsemester.burhanuddin.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.tugasakhirsemester.burhanuddin.R
import com.tugasakhirsemester.burhanuddin.databinding.ActivityGlobalDataBinding
import com.tugasakhirsemester.burhanuddin.model.GlobalDeathResponse
import com.tugasakhirsemester.burhanuddin.model.GlobalPositifResponse
import com.tugasakhirsemester.burhanuddin.model.GlobalRecoverResponse
import com.tugasakhirsemester.burhanuddin.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GlobalDataActivity : AppCompatActivity(), View.OnClickListener{

    /*
    * Sebelumnya untuk penggunaan ViewBinding kita harus katifkan di build.gradle module-nya
    */
    private val BindingGlobal by lazy { ActivityGlobalDataBinding.inflate(layoutInflater) }

    /*
    * Alasan prtama saya menggunakan ViewBinding dikarenakan android studio yang saya gunakan
    * adalah versi paling baru yaitu 4.1.1, pada android studio versi lama menggunakan
    * Kotlin syintetic dimana kita tidak perlu menggunakan findViewById untuk
    * memanggil id view pada layout, dan di android studio versi baru
    * tidak bisa menggunakan itu kita harus menginisialisasinya
    * secara manual dengan menggunakan findViewById
    * dan kabarnya lagi dari developer anroid
    * studio kita di suruh segera migr
    * asi ke viewBinding
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(BindingGlobal.root)

        GlobalPositifResponse()
        GlobalRecoverResponse()
        GlobalDeathResponse()

        //setOnClickListener pada ImageView dengan id view di bawah ini
        BindingGlobal.goBeranda.setOnClickListener(this)
        BindingGlobal.maps.setOnClickListener(this)
        BindingGlobal.googleBerita.setOnClickListener(this)
    }

    /*
    * Pada activity ini hampir sama pada clas kotlin IndonesiaDataActivity hany saja
    * pada class ini kita tidak menggunakan arry pada body response karna
    * bentuk API nya adalah dalam bentuk array Json sedangkan pada
    * activity ini data yang di ambil tidak berbentuk array
    */

    override fun onClick(Click: View) {
        when(Click.id){
            /*
            * Jika user click ImageView dengan id goBeranda maka akan dipinda
            * hkan ke aktivity beranda yaitu IndonesiaDataActivity
            */
            R.id.goBeranda ->{
                val Kembali = Intent(this@GlobalDataActivity, IndonesiaDataActivity::class.java)
                startActivity(Kembali)
            }
            /*
            * ika user meng click ImageView dengan id maps maka akan di arahkan
            * ke sebuah browser dengan url sesuai dengan uriString
            */
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

    /*
     * Global Data Positif Response
     */
    private fun GlobalPositifResponse(){
        ApiService.instance.getPositif().enqueue(object : Callback<GlobalPositifResponse>{
            //Jika sukses berkomunikasi ke server
            override fun onResponse(
                call: Call<GlobalPositifResponse>, response: Response<GlobalPositifResponse>)
            {
                val TV_GlobalPositifResponse = response.body()
                val TotalPositif = TV_GlobalPositifResponse?.value

                BindingGlobal.GLPositif.text = TotalPositif
            }
            //Dan ini jika ada ganguan saat request ke server
            override fun onFailure(call: Call<GlobalPositifResponse>, t: Throwable) {
                Toast.makeText(this@GlobalDataActivity,"Check your internet connection and try again to connect", Toast.LENGTH_SHORT).show()
            }
        })
    }

    /*
    * Global Data Recover Response
    */
    private fun GlobalRecoverResponse(){
        ApiService.instance.getSembuh().enqueue(object : Callback<GlobalRecoverResponse>{
            //Jika sukses berkomunikasi ke server
            override fun onResponse(
                call: Call<GlobalRecoverResponse>, response: Response<GlobalRecoverResponse>)
            {
                val TV_GlobalRecoverResponse = response.body()
                val TotalSembuh = TV_GlobalRecoverResponse?.value

                BindingGlobal.GLRecover.text = TotalSembuh
            }

            //Dan ini jika ada ganguan saat request ke server
            override fun onFailure(call: Call<GlobalRecoverResponse>, t: Throwable) {
                Toast.makeText(this@GlobalDataActivity,"Check your internet connection and try again to connect", Toast.LENGTH_SHORT).show()
            }
        })
    }

    /*
    * Global Data Death Response
    */
    private fun GlobalDeathResponse(){
        ApiService.instance.getMeninggal().enqueue(object : Callback<GlobalDeathResponse>{
            //Jika sukses berkomunikasi ke server
            override fun onResponse(
                call: Call<GlobalDeathResponse>, response: Response<GlobalDeathResponse>)
            {
                val TV_GlobalDeathResponse = response.body()
                val TotalMeninggal = TV_GlobalDeathResponse?.value

                BindingGlobal.GLDeath.text = TotalMeninggal
            }
            //Dan ini jika ada ganguan saat request ke server
            override fun onFailure(call: Call<GlobalDeathResponse>, t: Throwable) {
                Toast.makeText(this@GlobalDataActivity,"Check your internet connection and try again to connect", Toast.LENGTH_SHORT).show()
            }
        })
    }
}