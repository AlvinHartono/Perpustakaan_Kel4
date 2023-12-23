package com.example.perpustakaan_kel4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.perpustakaan_kel4.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var member : Member
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(binding.root)

        val bundle = intent.extras

        val phoneNumber: String? = bundle!!.getString("no_telp")

        if (phoneNumber != null) {
            Log.d("phone number", phoneNumber)
        }

        getMemberInfo(phoneNumber)
        replaceFragment(Home(), phoneNumber)


        //Delete account:
        //ambil data member dari database
        //masukin data member yg diambil ke Model Member
        //delete akun berdasarkan id member yg ada di model member.
        //done


        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(Home(), phoneNumber)
                R.id.bookings -> replaceFragment(Bookings(), phoneNumber)
                R.id.history -> replaceFragment(History(), phoneNumber)
                R.id.account -> replaceFragment(Account(), phoneNumber)
                else -> {}
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

    private fun getMemberInfo(phoneNumber: String?) {
        val url: String = ApiEndPoint.READ_MEMBER
        val stringRequest = object : StringRequest(
            Method.POST,
            url,
            Response.Listener { response ->
                Log.d("response", response)
//                TODO: masukkan logic json ke map trus masuk ke variabel brodi
                val jsonObj =JSONObject(response)
                member.id_member = jsonObj.getString("id_member")
                member.first_name_member = jsonObj.getString("first_name_member")
                member.last_name_member = jsonObj.getString("last_name_member")
                member.email = jsonObj.getString("email")
                member.no_telp = jsonObj.getString("no_telp")
                member.password = jsonObj.getString("password")

                Log.d("isi member", " ${member.id_member}, ${member.first_name_member}, ${member.last_name_member}, ${member.email}")

            },
            Response.ErrorListener { response ->
                Log.d("response", response.toString())
            }){
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["no_telp"] = phoneNumber.toString()
                return params
            }
        }
        Volley.newRequestQueue(this).add(stringRequest)
    }
}