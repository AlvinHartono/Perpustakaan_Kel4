package com.example.perpustakaan_kel4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewBookAdapter(private val getActivity: FragmentActivity, private val booklist: List<Buku>) :
    RecyclerView.Adapter<RecyclerViewBookAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_books_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return booklist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val bookimg : ImageView = itemView.findViewById(R.id.imgBook)
        val cardView : CardView = itemView.findViewById(R.id.cardViewList)

    }
}