package com.example.perpustakaan_kel4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.perpustakaan_kel4.databinding.ActivityMainBinding

class MainActivityLibrarian : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_librarian)

        val bundle = intent.extras
        val phoneNumber = bundle!!.getString("no_telp")
        if (phoneNumber != null) {
            Log.d("response", phoneNumber)
        }


        binding.bottomNavigationView.setOnItemSelectedListener{
            when (it.itemId) {
                R.id.home_librarian -> replaceFragment(HomeLibrarian())

                R.id.books_librarian -> replaceFragment(BooksLibrarian())

                R.id.member_librarian -> replaceFragment(MembersLibrarian())

                R.id.transactions_librarian -> replaceFragment(TransactionLibrarian())

                R.id.account_librarian -> replaceFragment(AccountLibrarian())

                else -> {}
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