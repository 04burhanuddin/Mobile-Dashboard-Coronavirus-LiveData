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
import com.tugasakhirsemester.burhanuddin.datas.DataIndonesiaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IndonesiaDataActivity : AppCompatActivity() {

    private val Binding by lazy { ActivityIndonesiaDataBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(Binding.root)
        showKasusIndonesia()

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
            override fun onResponse(
                call: Call<ArrayList<DataIndonesiaResponse>>,
                response: Response<ArrayList<DataIndonesiaResponse>>)
            {
                val indonesiaResponse = response.body()?.get(0)
                val CassPositif = indonesiaResponse?.positif
                val CassDirawat = indonesiaResponse?.dirawat
                val CassSembuh = indonesiaResponse?.sembuh
                val CassMeninggal = indonesiaResponse?.meninggal

                Binding.dsPositif.text = CassPositif
                Binding.dsDirawat.text =CassDirawat
                Binding.dsSembuh.text = CassSembuh
                Binding.dsMeninggal.text = CassMeninggal
            }

            override fun onFailure(call: Call<ArrayList<DataIndonesiaResponse>>, t: Throwable) {
                Toast.makeText(this@IndonesiaDataActivity,"Check your internet connection and try again to connect", Toast.LENGTH_SHORT).show()
            }
        })
    }
}