package com.example.perpustakaan_kel4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider

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
        var editImage : ImageView = view.findViewById(R.id.imageViewUpload) as ImageView
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

        }
        return view
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