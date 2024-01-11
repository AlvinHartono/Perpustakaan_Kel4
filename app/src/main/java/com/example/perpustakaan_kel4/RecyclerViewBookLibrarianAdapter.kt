package com.example.perpustakaan_kel4

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RecyclerViewBookLibrarianAdapter(
    private var BookList: List<Book>,
    private var bookCommunicator: BookCommunicator
) : RecyclerView.Adapter<RecyclerViewBookLibrarianAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imgBuku : ImageView = itemView.findViewById(R.id.LibrarianBook_imgBuku)
        val judulBuku : TextView = itemView.findViewById(R.id.librarianBook_judulBuku)

        val editbtn : ImageView = itemView.findViewById(R.id.EditBookImg)
        val delbtn : ImageView = itemView.findViewById(R.id.DeleteBookImg)

        val bookLibrarianCardView : CardView = itemView.findViewById(R.id.BookLibrarianCardView)
    }

    fun deleteBook(position: Int) {
        val mutableList =BookList.toMutableList()
        mutableList.removeAt(position)
        BookList = mutableList.toList()
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_book_librarian, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return BookList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentBook = BookList[position]

        holder.judulBuku.text = currentBook.judul_buku
        holder.imgBuku.setImageBitmap(currentBook.decodeByteArrayToBitmap(currentBook.image_buku))

        holder.editbtn.setOnClickListener {
            //ke fragment edit book
            bookCommunicator.editBookFragment(currentBook)
        }

        holder.delbtn.setOnClickListener {
            //delete pake delete buku php
            val url : String = ApiEndPoint.DELETE_BOOKS
            val stringRequest = object : StringRequest(
                Method.POST, url,
                Response.Listener { response ->
                    Log.d("response", response)

                    if (response.equals("success")) {
                        Toast.makeText(
                            holder.itemView.context,
                            "Book Deleted",
                            Toast.LENGTH_SHORT
                        ).show()
                        deleteBook(holder.adapterPosition)

                    } else {
                        Toast.makeText(
                            holder.itemView.context,
                            "Failed to delete book. Please try again later",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                },
                Response.ErrorListener { response ->
                    Log.d("response", response.toString())
                }
            ) {
                override fun getParams(): HashMap<String, String> {
                    val params = HashMap<String, String>()
                    params["id_buku"] = currentBook.id_buku.toString()
                    return params
                }
            }
            Volley.newRequestQueue(holder.itemView.context).add(stringRequest)


        }

        holder.bookLibrarianCardView.setOnClickListener {
            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle(currentBook.judul_buku)
                .setMessage(
                      "Penerbit : \t" + currentBook.penerbit
                    + "\nPengarang : \t" + currentBook.pengarang
                    + "\nKategori : \t" + currentBook.nama_kategori
                    + "\nTahun Terbit : \t" + currentBook.tahun_terbit
                )

                .setNegativeButton("Close") { dialog, which ->
                    // Handle the negative button click (Cancel)
                }
                .show()
        }

    }
    fun updateData(newList: List<Book>) {
        BookList = newList
        notifyDataSetChanged()
    }

}