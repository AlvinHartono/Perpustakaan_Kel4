package com.example.perpustakaan_kel4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.perpustakaan_kel4.databinding.ActivityMainLibrarianBinding
import org.json.JSONArray
import org.json.JSONObject

class MainActivityLibrarian : AppCompatActivity(), LibrarianCommunicator, BookCommunicator, BookingCommunicator {

    private lateinit var binding: ActivityMainLibrarianBinding
    private lateinit var librarianViewModel: LibrarianViewModel
    private lateinit var memberViewModel: MemberViewModel
    private lateinit var booksViewModel: BooksViewModel

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

        //Initialize MemberViewModel
        memberViewModel = ViewModelProvider(this)[MemberViewModel::class.java]

        //
        booksViewModel = ViewModelProvider(this)[BooksViewModel::class.java]

        //Fetching Librarian Data from the database
        getLibrarianInfo(phoneNumber)

        //Fetching Lists of Members from the database
        getAllMembers()

        //Fetching Lists of books from the database
        getAllBooks()

        //First Initial fragment
        replaceFragment(BooksLibrarian())

        //Binding fragments with each navbar
        binding.bottomNavigationViewLibrarian.setOnItemSelectedListener {
            when (it.itemId) {

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

    private fun getAllMembers() {
        val url: String = ApiEndPoint.READ_MEMBER_LIBRARIAN
        val stringRequest = object : StringRequest(
            Method.POST,
            url,
            Response.Listener { response ->
                try {
                    val jsonArray = JSONArray(response)

                    Log.d("response quantity", jsonArray.length().toString())
                    for (i in 0 until jsonArray.length()) {
                        Log.d("response member", i.toString())
                        val jsonObject = jsonArray.getJSONObject(i)
                        val member = Member(
                            jsonObject.getString("id_member"),
                            jsonObject.getString("first_name_member"),
                            jsonObject.getString("last_name_member"),
                            jsonObject.getString("email"),
                            jsonObject.getString("no_telp"),
                            jsonObject.getString("password"),
                        )

                        memberViewModel.insertBookList(member)
                        Log.d(
                            "response check viewmodel",
                            memberViewModel.currentMemberList.value!![i].first_name_member
                        )
                    }


                } catch (e: Throwable) {
                    Log.d("response fetch books", e.toString())
                }

            },
            Response.ErrorListener { response ->
                Log.d("data", response.toString())
            }) {

        }
        Volley.newRequestQueue(this).add(stringRequest)
    }
    private fun getAllBooks(){
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
        Volley.newRequestQueue(this).add(stringRequest)
    }

    //this function is doing okay
    override fun editLibrarianFragment() {
        replaceFragment(editLibrarianAccount())
    }

    //this function is doing okay
    override fun editMemberFragment(currentMember: Member) {
        replaceFragment(EditMemberFromLibrarian(currentMember))
    }


    //this function is doing okay
    override fun deleteMember(member: Member) {
        memberViewModel.deleteMember(member)
    }
    //???
    override fun booksToAddBooksFragment() {
        replaceFragment(AddBook())
    }


    override fun editBookFragment(currentBook : Book) {
        replaceFragment(EditBook(currentBook))
    }

    override fun editTransactionFragment(currentTransaction: Pinjam) {
        replaceFragment(EditTransactionFromLibrarian())
    }

    override fun deleteBook(currentBook: Book) {
        booksViewModel.deleteBook(currentBook)
    }

}