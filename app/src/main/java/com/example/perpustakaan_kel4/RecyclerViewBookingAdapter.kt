package com.example.perpustakaan_kel4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewBookingAdapter(
    private var bookings: List<Pinjam>
) :
    RecyclerView.Adapter<RecyclerViewBookingAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookImg: ImageView = itemView.findViewById(R.id.booking_list_img)
        val judulbuku: TextView = itemView.findViewById(R.id.list_judulbuku)
        val tglpinjam: TextView = itemView.findViewById(R.id.list_tglpinjam)
        val status: TextView = itemView.findViewById(R.id.list_status)
        val warning: ImageView = itemView.findViewById(R.id.warning_icon)
        val btstglkembali: TextView = itemView.findViewById(R.id.list_bts_tgl_pengembalian)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_booking, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookings.size
    }

    fun updateData(newList: List<Pinjam>) {
        bookings = newList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentBooking = bookings[position]

        holder.judulbuku.text = currentBooking.judul_buku
        holder.tglpinjam.text = currentBooking.tgl_peminjaman.toString()
        holder.bookImg.setImageBitmap(currentBooking.decodeByteArrayToBitmap(currentBooking.image_buku))

        if (currentBooking.status) {
            holder.status.text = "Sudah Dikembalikan pada " + currentBooking.tgl_pengembalian
            holder.warning.isInvisible = true
            holder.btstglkembali.isInvisible = true

        } else {
            holder.status.text = "Belum dikembalikan"
            holder.btstglkembali.text = currentBooking.batas_tgl_pengembalian.toString()
        }


    }
}