package com.example.perpustakaan_kel4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.perpustakaan_kel4.databinding.ActivityMainLibrarianBinding
import org.json.JSONObject

class MainActivityLibrarian : AppCompatActivity(), LibrarianCommunicator {

    private lateinit var binding: ActivityMainLibrarianBinding
    private lateinit var librarianViewModel: LibrarianViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainLibrarianBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(binding.root)

        val bundle = intent.extras
        val phoneNumber = bundle!!.getString("no_telp")
        if (phoneNumber != null) {
            Log.d("response phone number", phoneNumber)
        }

        //Initialize LibrarianViewModel
        librarianViewModel = ViewModelProvider(this)[LibrarianViewModel::class.java]
        getLibrarianInfo(phoneNumber)
        replaceFragment(BooksLibrarian())

        binding.bottomNavigationViewLibrarian.setOnItemSelectedListener {
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
        Log.d("response", "creating fragment")

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout_librarian, fragment)
        fragmentTransaction.commit()
    }


    private fun getLibrarianInfo(phoneNumber: String?) {
        val url: String = ApiEndPoint.READ_LIBRARIAN
        val stringRequest = object : StringRequest(
            Method.POST,
            url,
            Response.Listener { response ->
                val jsonObj = JSONObject(response)

                //updated data
                val updatedLibrarian = Librarian(
                    jsonObj.getString("id_librarian"),
                    jsonObj.getString("first_name_librarian"),
                    jsonObj.getString("last_name_librarian"),
                    jsonObj.getString("email"),
                    jsonObj.getString("no_telp"),
                    jsonObj.getString("password"),
                )
                Log.d("response updatedMember", updatedLibrarian.toString())

                librarianViewModel.updateLibrarianData(updatedLibrarian)

                Log.d("response", librarianViewModel.currentLibrarian.value!!.first_name_librarian)

            },
            Response.ErrorListener { response ->
                Log.d("data", response.toString())
            }) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["no_telp"] = phoneNumber.toString()
                return params
            }
        }
        Volley.newRequestQueue(this).add(stringRequest)
    }

    override fun editLibrarianFragment() {
        Log.d("response editacc", "yo")
        replaceFragment(editLibrarianAccount())
        Log.d("response editacc", "yo end")
    }

}