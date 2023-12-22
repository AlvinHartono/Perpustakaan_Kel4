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

        val bundle = intent.extras
        var phoneNumber: String? = null

        phoneNumber = bundle!!.getString("no_telp")

        if (phoneNumber != null) {
            Log.d("phone number", phoneNumber)
        }

        replaceFragment(Home(), phoneNumber)


        //Delete account:
        //ambil data member dari database
        //masukin data member yg diambil ke Model Member
        //delete akun berdasarkan id member yg ada di model member.
        //done


        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(Home(), phoneNumber)
                R.id.bookings -> replaceFragment(Bookings(), phoneNumber)
                R.id.history -> replaceFragment(History(), phoneNumber)
                R.id.account -> replaceFragment(Account(), phoneNumber)
                else ->{}
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment, no_telp: String?) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        //tambah bundle ke fragment
        val mBundle = Bundle()
        mBundle.putString("no_telp", no_telp)
        fragment.arguments = mBundle

        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}