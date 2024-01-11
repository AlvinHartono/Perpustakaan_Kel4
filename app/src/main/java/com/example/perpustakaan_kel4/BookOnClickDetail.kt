package com.example.perpustakaan_kel4

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BookOnClickDetail.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookOnClickDetail(book: Book, memberID: String) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var book: Book = book
    private var memberID = memberID

    private lateinit var bookingViewModel: BookingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        bookingViewModel = ViewModelProvider(this)[BookingViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_book_on_click_detail, container, false)

        val image: ImageView = view.findViewById<View>(R.id.bookIMG) as ImageView

        val judulBuku: TextView = view.findViewById<View>(R.id.judulBuku) as TextView
        val namaPenerbit: TextView = view.findViewById<View>(R.id.nama_penerbit) as TextView
        val namaPengarang: TextView = view.findViewById<View>(R.id.nama_pengarang) as TextView
        val tahunTerbit: TextView = view.findViewById<View>(R.id.tahun_terbit) as TextView
        val namaKategori: TextView = view.findViewById<View>(R.id.nama_kategori) as TextView
        val buttonPinjam: Button = view.findViewById<View>(R.id.btnPinjam) as Button

        image.setImageBitmap(book.decodeByteArrayToBitmap(book.image_buku))

        judulBuku.text = book.judul_buku
        namaPenerbit.text = book.penerbit
        namaPengarang.text = book.pengarang
        tahunTerbit.text = book.tahun_terbit
        namaKategori.text = book.nama_kategori

        buttonPinjam.setOnClickListener {
            val url: String = ApiEndPoint.ADD_PINJAM
            val stringRequest = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener { response ->
                    try {
                        // Parse response as a JSONObject
                        val jsonObject = JSONObject(response)

                        if (jsonObject.getString("fail") == "fail") {
                            Toast.makeText(
                                requireContext(),
                                "Book is not available",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        if (jsonObject.has("id_buku")) {
                            // Extract values from the JSONObject
                            val pinjam = Pinjam()
                            pinjam.id_buku = book.id_buku.toString()
                            pinjam.id_member = memberID
                            pinjam.tgl_peminjaman = jsonObject.getString("tgl_peminjaman")
                            pinjam.tgl_pengembalian = jsonObject.getString("tgl_pengembalian")
                            pinjam.batas_tgl_pengembalian =
                                jsonObject.getString("batas_tgl_pengembalian")
                            pinjam.status = false
                            pinjam.image_buku = book.image_buku
                            pinjam.judul_buku = book.judul_buku

                            Log.d("PINJAM ISI", pinjam.id_buku)

                            // Now you can use pinjam object as needed
                            bookingViewModel.insertBookingList(pinjam)
                            Toast.makeText(requireContext(), "Booking Succesfull", Toast.LENGTH_SHORT)
                                .show()
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
                    params["id_member"] = memberID
                    return params
                }
            }

            Volley.newRequestQueue(requireContext()).add(stringRequest)
        }




        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val back: ImageView = view.findViewById<View>(R.id.backbtn) as ImageView

        back.setOnClickListener {
            closeCurrentFragment()
        }
    }


    private fun closeCurrentFragment() {
        // Get the fragment manager
        val fragmentManager = requireActivity().supportFragmentManager

        // Begin a fragment transaction
        val transaction = fragmentManager.beginTransaction()

        // replacing the current fragment
        transaction.replace(R.id.frame_layout, Home())
        // Commit the transaction
        transaction.commit()

        // Optionally, you can add the following line to allow the user to navigate back
        // fragmentManager.popBackStack()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BookOnClickDetail.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String, book: Book, memberID: String) =
            BookOnClickDetail(book, memberID).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}