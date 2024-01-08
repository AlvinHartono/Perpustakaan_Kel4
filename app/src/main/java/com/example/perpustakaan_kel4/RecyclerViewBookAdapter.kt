package com.example.perpustakaan_kel4

import android.content.Intent
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

class RecyclerViewBookAdapter(private val booklist: List<Book>) :
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
            Toast.makeText(holder.itemView.context, currentBook.judul_buku, Toast.LENGTH_LONG)
                .show()

            val fragment = bookMemberOnClickDetailPage()

            val fragmentManager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
            val transaction = fragmentManager.beginTransaction()

            transaction.replace(R.id.book_member_on_click_detail_page, fragment)
            transaction.addToBackStack(null)

            transaction.commit()

            //holder.cardView.context.startActivity(Intent(holder.cardView.context, bookMemberOnClickDetail::class.java))
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookImg: ImageView = itemView.findViewById(R.id.ivBookImg)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val bookTitle: TextView = itemView.findViewById(R.id.BookTitle)
    }
}
