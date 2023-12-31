package com.example.perpustakaan_kel4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewBookingAdapter(
    private val bookings: List<Pinjam>, private var bookingCommunicator: BookingCommunicator) :
    RecyclerView.Adapter<RecyclerViewBookingAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookImg : ImageView = itemView.findViewById(R.id.booking_list_img)
        val judulbuku : TextView = itemView.findViewById(R.id.list_judulbuku)
        val tglpinjam : TextView = itemView.findViewById(R.id.list_tglpinjam)
        val status : TextView = itemView.findViewById(R.id.list_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_booking, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookings.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentBooking = bookings[position]


    }
}