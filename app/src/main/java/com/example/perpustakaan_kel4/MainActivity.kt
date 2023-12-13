package com.example.perpustakaan_kel4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
//    ini variable waktu buat backpress
//    var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

//    ini kode harusnya buat app close kalo user back 2 kali, tapi belum berhasil.
//    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
//        override fun handleOnBackPressed() {
//            if (backPressedTime + 3000 > System.currentTimeMillis()) {
//                onBackPressedDispatcher.onBackPressed()
//                finish()
//            } else {
//                Toast.makeText(
//                    this@MainActivity,
//                    "Press back again to leave the app.",
//                    Toast.LENGTH_LONG
//                ).show()
//
//            }
//            backPressedTime = System.currentTimeMillis()
//        }
//    }
}