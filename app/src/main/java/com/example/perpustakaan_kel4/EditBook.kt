package com.example.perpustakaan_kel4

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
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import java.io.ByteArrayOutputStream
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditBook.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditBook(book: Book) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var book : Book = book
    private lateinit var  booksViewModel: BooksViewModel

    private lateinit var editImage: ImageView
    private var pickedBitmap: Bitmap? = null
    private lateinit var byteArray : ByteArray
    private var resId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        booksViewModel = ViewModelProvider(requireActivity())[BooksViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_book, container, false)
        var backButton : ImageView = view.findViewById(R.id.backbtneditbook) as ImageView
        editImage = view.findViewById(R.id.imageViewUpload)
        var judul_buku : EditText = view.findViewById(R.id.judulBukuedit) as EditText
        var penerbit : EditText = view.findViewById(R.id.Penerbitedit) as EditText
        var pengarang : EditText = view.findViewById(R.id.Pengarangedit) as EditText
        var tahun_terbit : EditText = view.findViewById(R.id.tahunTerbitedit) as EditText
        var kategori : EditText = view.findViewById(R.id.id_kategoriedit) as EditText
        var cancel : Button = view.findViewById(R.id.editbookionCancel) as Button
        var save : Button = view.findViewById(R.id.editbookSave) as Button

        judul_buku.setText(book.judul_buku)
        penerbit.setText(book.penerbit)
        pengarang.setText(book.pengarang)
        tahun_terbit.setText(book.tahun_terbit)
        kategori.setText(book.nama_kategori)
        kategori.isEnabled = false
        editImage.setImageBitmap(book.decodeByteArrayToBitmap(book.image_buku))

        cancel.setOnClickListener {
            closeCurrentFragment()
        }

        backButton.setOnClickListener {
            closeCurrentFragment()
        }
        editImage.setOnClickListener {
            Toast.makeText(requireContext(), "clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            activityResultLauncher.launch(intent)
        }

        save.setOnClickListener {
            if(pickedBitmap != null){
                val stream = ByteArrayOutputStream()
                pickedBitmap!!.compress(Bitmap.CompressFormat.PNG, 100, stream)
                byteArray = stream.toByteArray()

//                Log.d("response bjr", Base64.encodeToString(byteArray, Base64.DEFAULT))

            }
            //save
            val url: String = ApiEndPoint.UPDATE_BOOK
            val stringRequest = object : StringRequest(
                Method.POST, url,
                Response.Listener { response ->
                    try {
                        if (response == "success") {
                            var newBook = Book()
                            newBook.judul_buku = judul_buku.text.toString()
                            newBook.penerbit = penerbit.text.toString()
                            newBook.pengarang = pengarang.text.toString()
                            newBook.tahun_terbit = tahun_terbit.text.toString()
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

                            booksViewModel.updateBookData(book = newBook)

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
                    params["id_buku"] = book.id_buku.toString()
                    params["judul_buku"] = judul_buku.text.toString()
                    params["penerbit"] = penerbit.text.toString()
                    params["pengarang"] = pengarang.text.toString()
                    params["tahun_terbit"] = tahun_terbit.text.toString()
                    params["id_kategori"] = kategori.text.toString()
                    params["image_buku"] = Base64.encodeToString(byteArray, Base64.DEFAULT)
                    return params
                }
            }

            Volley.newRequestQueue(requireContext()).add(stringRequest)
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
                    editImage.setImageBitmap(pickedBitmap)
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
         * @return A new instance of fragment EditBook.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String, book: Book) =
            EditBook(book).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}