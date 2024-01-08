package com.example.perpustakaan_kel4

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [bookMemberOnClickDetailPage.newInstance] factory method to
 * create an instance of this fragment.
 */
class bookMemberOnClickDetailPage : Fragment() {
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
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(
            R.layout.fragment_book_member_on_click_detail_page,
            container,
            false
        )

        var judul_buku : TextView = view.findViewById(R.id.judulBuku) as TextView
        var nama_penerbit : TextView = view.findViewById(R.id.nama_penerbit) as TextView
        var nama_pengarang : TextView = view.findViewById(R.id.nama_pengarang) as TextView
        var nama_kategori : TextView = view.findViewById(R.id.nama_kategori) as TextView
        var gmbr : ImageView = view.findViewById(R.id.bookIMG) as ImageView

        booksViewModel.currentBookList.observe(requireActivity(), Observer { bookList ->
            val titles = bookList.map { it.judul_buku }
            val concatenatedTitles = titles.joinToString("\n")
            judul_buku.text = concatenatedTitles

            val penerbits = bookList.map { it.penerbit }
            val concatenatedPenerbits = penerbits.joinToString("\n")
            nama_penerbit.text = concatenatedPenerbits

            val pengarangs = bookList.map { it.pengarang }
            val concatenatedPengarangs = pengarangs.joinToString("\n")
            nama_pengarang.text = concatenatedPengarangs

            val kategoris = bookList.map { it.nama_kategori }
            val concatenatedKategoris = kategoris.joinToString("\n")
            nama_kategori.text = concatenatedKategoris
        })

        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment bookMemberOnClickDetailPage.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            bookMemberOnClickDetailPage().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}