package com.example.perpustakaan_kel4

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddBook.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddBook : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_book, container, false)

        val btnsave : Button = view.findViewById(R.id.addBookSave)
        val btncancel : Button = view.findViewById(R.id.addBookCancel)
        val btnback : ImageView = view.findViewById(R.id.backbtn)

        val judulbuku : TextView = view.findViewById(R.id.judulBuku)
        val penerbit : TextView = view.findViewById(R.id.Penerbit)
        val thnterbit : TextView = view.findViewById(R.id.tahunTerbit)
        val kategori : TextView = view.findViewById(R.id.id_kategori)
        val imagebuku : ImageView = view.findViewById(R.id.imageView)
        val pengarang : TextView = view.findViewById(R.id.pengarang)

        btnsave.setOnClickListener {
            //save
            var newBook = Book()
            newBook.judul_buku = judulbuku.toString()
            newBook.penerbit = penerbit.toString()
            newBook.pengarang = pengarang.toString()
            newBook.tahun_terbit = thnterbit.toString()

        }

        btncancel.setOnClickListener {
            closeCurrentFragment()
        }

        btnback.setOnClickListener {
            closeCurrentFragment()
        }

        return view
    }

    private fun closeCurrentFragment() {
        // Get the fragment manager
        val fragmentManager = requireActivity().supportFragmentManager

        // Begin a fragment transaction
        val transaction = fragmentManager.beginTransaction()

        // replacing the current fragment
        transaction.replace(R.id.frame_layout, BooksLibrarian())
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