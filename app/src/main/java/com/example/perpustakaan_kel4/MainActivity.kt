package com.example.perpustakaan_kel4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.perpustakaan_kel4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(binding.root)
        replaceFragment(Home())


        //Delete account:
        //ambil data member dari database
        //masukin data member yg diambil ke Model Member
        //delete akun berdasarkan id member yg ada di model member.
        //done

        val bundle = intent.extras
        var phoneNumber: String? = null

        phoneNumber = bundle!!.getString("no_telp")

        if (phoneNumber != null) {
            Log.d("phone number", phoneNumber)
        }


        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(Home())
                R.id.bookings -> replaceFragment(Bookings())
                R.id.history -> replaceFragment(History())
                R.id.account -> replaceFragment(Account())
                else ->{}
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}