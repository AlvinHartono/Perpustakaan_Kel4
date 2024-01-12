package com.example.perpustakaan_kel4

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.perpustakaan_kel4.databinding.FragmentAddBookBinding
import org.json.JSONException
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddBook.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class AddBook : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var pickedBitmap: Bitmap? = null
    private lateinit var byteArray : ByteArray
    private var resId = 0
    private lateinit var imagebuku: ImageView

    private lateinit var binding: FragmentAddBookBinding
    private lateinit var booksViewModel: BooksViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        booksViewModel = ViewModelProvider(requireActivity())[BooksViewModel::class.java]

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBookBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_book, container, false)

        val btnsave: Button = view.findViewById(R.id.addBookSave)
        val btncancel: Button = view.findViewById(R.id.addBookCancel)
        val btnback: ImageView = view.findViewById(R.id.backbtn)

        val judulbuku: TextView = view.findViewById(R.id.judulBukuadd)
        val penerbit: TextView = view.findViewById(R.id.Penerbit)
        val thnterbit: TextView = view.findViewById(R.id.tahunTerbit)
        val kategori: TextView = view.findViewById(R.id.id_kategori)
        imagebuku = view.findViewById(R.id.imageViewadd)
        val pengarang: TextView = view.findViewById(R.id.pengarang)

        btnsave.setOnClickListener {

            if(pickedBitmap != null){
                val stream = ByteArrayOutputStream()
                pickedBitmap!!.compress(Bitmap.CompressFormat.PNG, 100, stream)
                byteArray = stream.toByteArray()

            }
            //save
            val url: String = ApiEndPoint.ADD_BOOK
            val stringRequest = object : StringRequest(
                Method.POST, url,
                Response.Listener { response ->
                    try {
                        if (response == "success") {
                            var newBook = Book()
                            newBook.judul_buku = judulbuku.text.toString()
                            newBook.penerbit = penerbit.text.toString()
                            newBook.pengarang = pengarang.text.toString()
                            newBook.tahun_terbit = thnterbit.text.toString()
                            if (kategori.text.toString().toInt() == 1) {
                                newBook.nama_kategori = "Adventure"
                            } else if (kategori.text.toString().toInt() == 2) {
                                newBook.nama_kategori = "Fiction"
                            } else if (kategori.text.toString().toInt() == 3) {
                                newBook.nama_kategori = "Novel"
                            } else if (kategori.text.toString().toInt() == 4) {
                                newBook.nama_kategori = "Anthology"
                            } else if (kategori.text.toString().toInt() == 5) {
                                newBook.nama_kategori = "Bibliography"
                            } else if (kategori.text.toString().toInt() == 6) {
                                newBook.nama_kategori = "Autobiography"
                            } else {
                                newBook.nama_kategori = "null"
                            }
                            if(pickedBitmap != null){
                                newBook.image_buku = byteArray
                            }

                            booksViewModel.insertBookList(newBook = newBook)

                            Handler(Looper.getMainLooper()).postDelayed({
                                closeCurrentFragment()
                            }, 1000)

                        } else  {
                            Log.d("responseError", response)
                            Log.d("response kategori", kategori.text.toString())
                            Toast.makeText(
                                requireContext(),
                                response,
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    } catch (e: JSONException) {
                        Log.e("JSON Parsing Error", e.toString())
                    }
                },
                Response.ErrorListener { error ->
                    Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()
                    Log.d("responseError", error.toString())
                }
            ) {
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["judul_buku"] = judulbuku.text.toString()
                    params["penerbit"] = penerbit.text.toString()
                    params["pengarang"] = pengarang.text.toString()
                    params["tahun_terbit"] = thnterbit.text.toString()
                    params["id_kategori"] = kategori.text.toString()
                    params["image_buku"] = Base64.encodeToString(byteArray, Base64.DEFAULT)
                    return params
                }
            }

            Volley.newRequestQueue(requireContext()).add(stringRequest)

        }

        btncancel.setOnClickListener {
            closeCurrentFragment()
        }

        btnback.setOnClickListener {
            closeCurrentFragment()
        }

        imagebuku.setOnClickListener {
            Toast.makeText(requireContext(), "clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            activityResultLauncher.launch(intent)
        }

        return view
    }

    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val uri = data?.data
                try {
                    pickedBitmap =
                        MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
                    imagebuku.setImageBitmap(pickedBitmap)
                    resId = 1
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }


    private fun closeCurrentFragment() {
        // Get the fragment manager
        val fragmentManager = requireActivity().supportFragmentManager

        // Begin a fragment transaction
        val transaction = fragmentManager.beginTransaction()

        // replacing the current fragment
        transaction.replace(R.id.frame_layout_librarian, BooksLibrarian())
        // Commit the transaction
        transaction.commit()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddBook.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddBook().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}