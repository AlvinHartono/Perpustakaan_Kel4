package com.example.perpustakaan_kel4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.perpustakaan_kel4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val bundle = intent.extras
    val message = bundle?.getString("no_telp")

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(binding.root)
        replaceFragment(Home())


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
        val args = Bundle()
        args.putString("key", message)
        fragment.arguments = args
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}