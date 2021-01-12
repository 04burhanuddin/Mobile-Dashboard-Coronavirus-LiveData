package com.tugasakhirsemester.burhanuddin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import android.widget.Toast
import com.tugasakhirsemester.burhanuddin.R
import com.tugasakhirsemester.burhanuddin.databinding.ActivityIndonesiaDataBinding
import com.tugasakhirsemester.burhanuddin.retrofit.ApiService
import com.tugasakhirsemester.burhanuddin.model.DataIndonesiaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IndonesiaDataActivity : AppCompatActivity() {

    /*
    * Sebelumnya untuk penggunaan ViewBinding bita harus setting kita harus katifkan di build.gradle-nya
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
    private val Binding by lazy { ActivityIndonesiaDataBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(Binding.root)
        showKasusIndonesia()

        /*
        * Mengetur sebuah id yang ada pada PopupMenu yang dimana jika user meng click
        * ImageView Dengan id showUpdate maka akan menampilkan popup menu yang
        * kemudian pada popup menu itu terdapat sebuah TextView yang apa
        * bila di click akan mengarah ke sebuah aktivity yaitu
        * activity GlobalDataActivity, serta adanya toast
        * dan pesan pada toast ini sama akan tampil
        * saat TextView di click dengan pesan
        * di bawah ini Data source.....
        */

        Binding.showUpdate.setOnClickListener{
            val ShowPopupMenu = PopupMenu(this, it)
            ShowPopupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId){
                    R.id.check_update -> {
                        val globalDataCovid = Intent(this@IndonesiaDataActivity, GlobalDataActivity::class.java)
                        startActivity(globalDataCovid)
                        Toast.makeText(getApplicationContext(),"Data source from kowalcorona.com",Toast.LENGTH_SHORT).show();
                        true
                    }
                    else -> false
                }
            }

            /*
            * Pada bagian ini di fungsikan untuk menampilkan PopupMenu serta icon yang ada
            * pada PopupMenu dengan dengan menggunakn influte yang sering diguna kan
            * saat kita ingin menambah layout pada layout utama kita dengan meng
            * gunakan exception try catc dan finally PopupMenu di tampilkan
            */
            ShowPopupMenu.inflate(R.menu.menu_info)
            try {
                val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
                fieldMPopup.isAccessible = true
                val mPopup = fieldMPopup.get(ShowPopupMenu)
                mPopup.javaClass
                    .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                    .invoke(mPopup, true)
            } catch (e: Exception){
                Log.e("Main", "Error showing menu icons.", e)
            } finally {
                ShowPopupMenu.show()
            }
        }
    }

    private fun showKasusIndonesia(){
        ApiService.instance.getDataIndonesia().enqueue(object : Callback<ArrayList<DataIndonesiaResponse>>{
            /*
            * Pada bagian ini dimana kita menginplement sebuah method di object method yang kita
            * implemen adalah onResponse dan onFailure dimana onrsponse adalah data akan di
            * tampilkan sesuai dengan get yang kita request ke server dan menampil
            * kannya di bagian response pada body di bawah ini dengan dan
            * karna dia dalam bentuk array maka getnya adalah get(0)
            */
            override fun onResponse(
                call: Call<ArrayList<DataIndonesiaResponse>>,
                response: Response<ArrayList<DataIndonesiaResponse>>)
            {
                //menginisialisasi respnse dari server
                val indonesiaResponse = response.body()?.get(0)
                val CassPositif = indonesiaResponse?.positif
                val CassDirawat = indonesiaResponse?.dirawat
                val CassSembuh = indonesiaResponse?.sembuh
                val CassMeninggal = indonesiaResponse?.meninggal

                /*
                * Dengan menggunakan ViewBinding kita bisa langsung mengetikkan Binding kemudian id
                * TextView dan di ikuti inisialisasi response yang sudah kita buat di atasdan
                * kemudian data akan di tampilka sesuai posisi yang sudah kita tentukan
                * pada layout root ViewBinding yaitu activity_indonesia_data
                */
                Binding.dsPositif.text = CassPositif
                Binding.dsDirawat.text =CassDirawat
                Binding.dsSembuh.text = CassSembuh
                Binding.dsMeninggal.text = CassMeninggal
            }
            /*
            * onFailure di sini kita gunakan sebagai pesan kepada user/pengguna aplikasi
            * jika terjadi masalah saat request/GET data ke srver dengan pesan
            * yang akan kita tampilkan menggunakan toast di bawah ini
            */
            override fun onFailure(call: Call<ArrayList<DataIndonesiaResponse>>, t: Throwable) {
                Toast.makeText(this@IndonesiaDataActivity,"Check your internet connection and try again to connect", Toast.LENGTH_SHORT).show()
            }
        })
    }
}