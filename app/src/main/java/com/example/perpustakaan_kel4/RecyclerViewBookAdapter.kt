package com.example.perpustakaan_kel4

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewBookAdapter(private val booklist: List<Book>,private var bookCommunicator: BookDetailCommunicator, private var memberID: String) :
    RecyclerView.Adapter<RecyclerViewBookAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_books_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return booklist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentBook = booklist[position]


        // Update views in the ViewHolder
        holder.bookTitle.text = currentBook.judul_buku
        holder.bookImg.setImageBitmap(currentBook.decodeByteArrayToBitmap(currentBook.image_buku))

        // Use itemView.context to get the context associated with the item view
        holder.cardView.setOnClickListener {
            try {

            bookCommunicator.BookDetailFragment(currentBook, memberID)


//            Toast.makeText(holder.itemView.context, currentBook.tahun_terbit, Toast.LENGTH_LONG)
//            Toast.makeText(holder.itemView.context, currentBook.judul_buku, Toast.LENGTH_SHORT)
//                .show()
            }catch (e: Throwable){
                Log.d("response adapter", e.toString())
            }

        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookImg: ImageView = itemView.findViewById(R.id.ivBookImg)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val bookTitle: TextView = itemView.findViewById(R.id.BookTitle)
    }
}
