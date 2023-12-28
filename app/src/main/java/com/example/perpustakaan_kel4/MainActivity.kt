package com.example.perpustakaan_kel4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.perpustakaan_kel4.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var memberViewModel: MemberViewModel
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(binding.root)

        val bundle = intent.extras
        val phoneNumber = bundle!!.getString("no_telp")
        if (phoneNumber != null) {
            Log.d("response", phoneNumber)
        }

        //Initialize MemberViewModel
        memberViewModel = ViewModelProvider(this)[MemberViewModel::class.java]
        getMemberInfo(phoneNumber)

        replaceFragment(Home())


        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(Home())

                R.id.bookings -> replaceFragment(Bookings())

                R.id.history -> replaceFragment(History())

                R.id.account -> replaceFragment(Account())

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

    private fun getMemberInfo(phoneNumber: String?) {
        val url: String = ApiEndPoint.READ_MEMBER
        val stringRequest = object : StringRequest(
            Method.POST,
            url,
            Response.Listener { response ->
//                Log.d("response", response)
                val jsonObj = JSONObject(response)

                //updated data
                val updatedMember = Member(
                    jsonObj.getString("id_member"),
                    jsonObj.getString("first_name_member"),
                    jsonObj.getString("last_name_member"),
                    jsonObj.getString("email"),
                    jsonObj.getString("no_telp"),
                    jsonObj.getString("password"),
                )
                Log.d("response updatedMember", updatedMember.toString())

                memberViewModel.updateMemberData(updatedMember)

                Log.d("response", memberViewModel.currentMember.value!!.first_name_member)

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
}