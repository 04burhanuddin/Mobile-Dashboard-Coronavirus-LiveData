package com.tugasakhirsemester.burhanuddin.model

 data class DataIndonesiaResponse(
     /*
      * Untuk neme val di bawah ini di sesuaikan dengan nama-nama
      * variabel yang telah di deklarasikan pada Api pada
      * https://api.kawalcorona.com/indonesia serta
      * tpe data di tentukan tipe datanya String
      */
     val name: String,
     val positif: String,
     val sembuh: String,
     val meninggal: String,
     val dirawat: String
 )
