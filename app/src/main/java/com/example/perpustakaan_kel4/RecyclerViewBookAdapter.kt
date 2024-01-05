package com.example.perpustakaan_kel4

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class RecyclerViewBookAdapter(
    private val getActivity: FragmentActivity,
    private val booklist: List<Buku>
) :
    RecyclerView.Adapter<RecyclerViewBookAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_books_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return booklist.size
    }

    @OptIn(ExperimentalEncodingApi::class)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val blobString = booklist[position].image_buku
        val imageBytes = Base64.decode(blobString)
        val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size);

//        val bitmap = convertBlobStringToBitmap(blobString)


        holder.bookTitle.text = booklist[position].judul_buku
        if (bitmap != null) {
            holder.bookimg.setImageBitmap(bitmap)

        }

    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookimg: ImageView = itemView.findViewById(R.id.imgBook)
        val bookTitle: TextView = itemView.findViewById(R.id.BookTitle)
        val cardView: CardView = itemView.findViewById(R.id.cardViewList)

    }
}