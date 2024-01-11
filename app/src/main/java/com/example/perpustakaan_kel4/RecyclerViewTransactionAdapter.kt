package com.example.perpustakaan_kel4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewTransactionAdapter(
    private var TransactionList: List<Pinjam>,
    private var bookingCommunicator: BookingCommunicator
) : RecyclerView.Adapter<RecyclerViewTransactionAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imgbuku : ImageView = itemView.findViewById(R.id.imgBuku)
        val judulBuku : TextView = itemView.findViewById(R.id.Transaction_BookName_ListView)
        val namaMember : TextView = itemView.findViewById(R.id.Transaction_MemberName_ListView)
        val tglpinjam : TextView = itemView.findViewById(R.id.Transaction_tglpeminjaman_ListView)

        val editbtn : ImageView = itemView.findViewById(R.id.EditBookImg)
        val deletebtn : ImageView = itemView.findViewById(R.id.DeleteBookImg)

        val cardview : CardView = itemView.findViewById(R.id.TransactionCardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_transaction, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return TransactionList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentTransaction = TransactionList[position]

        holder.namaMember.text = currentTransaction.nama_member
        //ganti ke nama
        holder.judulBuku.text = currentTransaction.judul_buku
        holder.tglpinjam.text = currentTransaction.tgl_peminjaman.toString()

        holder.deletebtn.setOnClickListener {
            //delete pake pinjam_delete.php
        }

        holder.editbtn.setOnClickListener {
            //pindah fragment
            bookingCommunicator.editTransactionFragment(currentTransaction)
        }

        holder.cardview.setOnClickListener {
            //pindah fragment atau tampilkan detail
        }

    }
}