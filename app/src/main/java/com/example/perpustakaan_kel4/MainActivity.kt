package com.example.perpustakaan_kel4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.android.volley.Request.Method
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.toolbox.Volley.*
import com.example.perpustakaan_kel4.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject
import android.util.Base64

class MainActivity : AppCompatActivity(), MemberCommunicator {


    private lateinit var binding: ActivityMainBinding


    //create viewModel variable that will be init later
    private lateinit var memberViewModel: MemberViewModel
    private lateinit var booksViewModel: BooksViewModel

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

        //Initialize BookViewModel
        booksViewModel = ViewModelProvider(this)[BooksViewModel::class.java]

        getMemberInfo(phoneNumber)
        getAllBooks()


        replaceFragment(Home())
        binding.bottomNavigationViewMember.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(Home())

                R.id.bookings -> replaceFragment(Bookings())

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
        newRequestQueue(this).add(stringRequest)
    }

    private fun getAllBooks() {
        val url: String = ApiEndPoint.READ_BOOKS
        val stringRequest = object : StringRequest(
            Method.POST,
            url,
            Response.Listener { response ->
                try {
                    val jsonArray = JSONArray(response)
                    Log.d("response", jsonArray.length().toString())
                    for (i in 0 until jsonArray.length()) {
                        Log.d("response book", i.toString())
                        val jsonObject = jsonArray.getJSONObject(i)
                        val book = Book()
                        book.id_buku = jsonObject.getString("id_buku").toInt();
                        book.judul_buku = jsonObject.getString("judul_buku");
                        book.penerbit = jsonObject.getString("penerbit");
                        book.pengarang = jsonObject.getString("pengarang");
                        book.nama_kategori = jsonObject.getString("nama_kategori");

                        val imageBase64 = jsonObject.getString("image_buku")
                        val imageByteArray: ByteArray = Base64.decode(imageBase64, Base64.DEFAULT)
                        book.image_buku = imageByteArray

                        booksViewModel.insertBookList(book)
                    }

                } catch (e: Throwable) {
                    Log.d("response fetch books", e.toString())
                }

            },
            Response.ErrorListener { response ->
                Log.d("data", response.toString())
            }) {

        }
        newRequestQueue(this).add(stringRequest)
    }

    override fun editMemberFragment() {
        replaceFragment(EditMemberAccount())
    }
}