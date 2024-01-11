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
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity(), MemberCommunicator, BookDetailCommunicator, BookingMemberComunicator{


    private lateinit var binding: ActivityMainBinding

    private lateinit var updatedMember: Member


    //create viewModel variable that will be init later
    private lateinit var memberViewModel: MemberViewModel
    private lateinit var booksViewModel: BooksViewModel
    private lateinit var bookingViewModel: BookingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(binding.root)

        val bundle = intent.extras
        val phoneNumber = bundle!!.getString("no_telp")
        if (phoneNumber != null) {
            Log.d("response no_telp", phoneNumber)
        }

        //Initialize MemberViewModel
        memberViewModel = ViewModelProvider(this)[MemberViewModel::class.java]

        //Initialize BookViewModel
        booksViewModel = ViewModelProvider(this)[BooksViewModel::class.java]

        //Initialize BookingViewModel
        bookingViewModel = ViewModelProvider(this)[BookingViewModel::class.java]


        getMemberInfo(phoneNumber)
        getAllBooks()

        Log.d("response done", "done")
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
        val stringRequest = object : StringRequest(Method.POST, url, Response.Listener { response ->
//                Log.d("response", response)
            val jsonObj = JSONObject(response)

            //updated data
            updatedMember = Member(
                jsonObj.getString("id_member"),
                jsonObj.getString("first_name_member"),
                jsonObj.getString("last_name_member"),
                jsonObj.getString("email"),
                jsonObj.getString("no_telp"),
                jsonObj.getString("password"),
            )
            getBookingData(jsonObj.getString("id_member"))
            Log.d("response updatedMember", updatedMember.toString())

            memberViewModel.updateMemberData(updatedMember)

            Log.d("response id_member", memberViewModel.currentMember.value!!.id_member)

        }, Response.ErrorListener { response ->
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
        val stringRequest = object : StringRequest(Method.POST, url, Response.Listener { response ->
            try {
                val jsonArray = JSONArray(response)
                Log.d("response books", jsonArray.length().toString())
                for (i in 0 until jsonArray.length()) {
                    Log.d("response books", i.toString())
                    val jsonObject = jsonArray.getJSONObject(i)
                    val book = Book()
                    book.id_buku = jsonObject.getString("id_buku").toInt()
                    book.judul_buku = jsonObject.getString("judul_buku")
                    book.penerbit = jsonObject.getString("penerbit")
                    book.pengarang = jsonObject.getString("pengarang")
                    book.tahun_terbit = jsonObject.getString("tahun_terbit")
                    book.nama_kategori = jsonObject.getString("nama_kategori")

                    val imageBase64 = jsonObject.getString("image_buku")
                    val imageByteArray: ByteArray = Base64.decode(imageBase64, Base64.DEFAULT)
                    book.image_buku = imageByteArray

                    booksViewModel.insertBookList(book)
                }

            } catch (e: Throwable) {
                Log.d("response fetch books", e.toString())
            }

        }, Response.ErrorListener { response ->
            Log.d("response error data", response.toString())
        }) {

        }
        newRequestQueue(this).add(stringRequest)
    }

    private fun getBookingData(memberID: String) {
        val url: String = ApiEndPoint.READ_PINJAM
        val stringRequest = object : StringRequest(Method.POST, url, Response.Listener { response ->
            Log.d("response function", response)
            try {
                val jsonArray = JSONArray(response)

                Log.d("response booking", jsonArray.length().toString())
                for (i in 0 until jsonArray.length()) {
                    Log.d("response bookings", i.toString())
                    val jsonObject = jsonArray.getJSONObject(i)
                    val pinjam = Pinjam()

                    pinjam.id_member = jsonObject.getString("id_member")
                    pinjam.id_buku = jsonObject.getString("id_buku")
                    pinjam.judul_buku = jsonObject.getString("judul_buku")


                    pinjam.tgl_peminjaman =
                        jsonObject.getString("tgl_peminjaman")

                    pinjam.tgl_pengembalian =
                        jsonObject.getString("tgl_pengembalian")

                    pinjam.batas_tgl_pengembalian =
                        jsonObject.getString("batas_tgl_pengembalian")
                    Log.d("response date 1", pinjam.tgl_peminjaman)
                    Log.d("response date 2", pinjam.tgl_pengembalian)
                    Log.d("response date 3", pinjam.batas_tgl_pengembalian)

                    if (jsonObject.getString("status") == "0") {
                        pinjam.status = false

                    } else if(jsonObject.getString("status") == "1"){
                        pinjam.status = true
                    }

                    Log.d("response book status", jsonObject.getString("status"))
                    Log.d("response book status", pinjam.status.toString())
                    val imageBase64 = jsonObject.getString("image_buku")
                    val imageByteArray: ByteArray = Base64.decode(imageBase64, Base64.DEFAULT)
                    pinjam.image_buku = imageByteArray

                    bookingViewModel.insertBookingList(pinjam)

                    Log.d("response booking isi", bookingViewModel.currentBooking.value!![i].id_member)
                    Log.d("response booking isi", bookingViewModel.currentBooking.value!![i].status.toString())
                    Log.d("response booking isi", bookingViewModel.currentBooking.value!![i].batas_tgl_pengembalian)
                    Log.d("response booking isi", bookingViewModel.currentBooking.value!![i].tgl_pengembalian)
                    Log.d("response booking isi", bookingViewModel.currentBooking.value!![i].tgl_peminjaman)
                }

            } catch (e: Throwable) {
                Log.d("response fetch books", e.toString())
            }

            Log.d("response function", "getbookingdata finish")
        }, Response.ErrorListener { response ->
            Log.d("data", response.toString())
        }) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["id_member"] = memberID
                return params
            }
        }
        newRequestQueue(this).add(stringRequest)
    }

    override fun editMemberFragment() {
        replaceFragment(EditMemberAccount())
    }

    override fun BookDetailFragment(book: Book, memberID: String) {
        replaceFragment(BookOnClickDetail(book, memberID))
    }

    override fun cancelBooking(booking: Pinjam) {
        bookingViewModel.cancelBooking(booking)
    }

}