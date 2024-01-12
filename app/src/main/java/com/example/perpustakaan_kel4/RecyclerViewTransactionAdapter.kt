package com.example.perpustakaan_kel4

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RecyclerViewTransactionAdapter(
    private var TransactionList: List<Pinjam>,
    private var bookingCommunicator: BookingCommunicator
) : RecyclerView.Adapter<RecyclerViewTransactionAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgbuku: ImageView = itemView.findViewById(R.id.imgBuku)
        val judulBuku: TextView = itemView.findViewById(R.id.Transaction_BookName_ListView)
        val namaMember: TextView = itemView.findViewById(R.id.Transaction_MemberName_ListView)
        val tglpinjam: TextView = itemView.findViewById(R.id.Transaction_tglpeminjaman_ListView)
        val returned: TextView = itemView.findViewById(R.id.textView25)

        val cancelButton: ImageView = itemView.findViewById(R.id.EditBookingImgTrans)
        val confirmButton: ImageView = itemView.findViewById(R.id.ReturnedBookingImgTrans)

        val cardview: CardView = itemView.findViewById(R.id.TransactionCardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_transaction, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return TransactionList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentTransaction = TransactionList[position]

        holder.namaMember.text = currentTransaction.id_member
        holder.judulBuku.text = currentTransaction.judul_buku
        holder.tglpinjam.text = currentTransaction.tgl_peminjaman.toString()
        holder.imgbuku.setImageBitmap(currentTransaction.decodeByteArrayToBitmap(currentTransaction.image_buku))

        if (currentTransaction.status) {
            holder.returned.isInvisible = false
            holder.cancelButton.isInvisible = true
            holder.confirmButton.isInvisible = true
        } else {
            holder.returned.isInvisible = true
            holder.cancelButton.isInvisible = false
            holder.confirmButton.isInvisible = false
        }

        holder.confirmButton.setOnClickListener {
            val url: String = ApiEndPoint.UPDATE_PINJAM
            val stringRequest = object : StringRequest(
                Method.POST, url,
                Response.Listener { response ->

                    if (response.equals("success")) {
                        Toast.makeText(
                            holder.itemView.context,
                            "Booking Confirmed",
                            Toast.LENGTH_SHORT
                        ).show()
                        bookingCommunicator.confirmedBooking(currentTransaction)
                    } else {
                        Toast.makeText(
                            holder.itemView.context,
                            response,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                Response.ErrorListener { response ->
                    Log.d("response", response.toString())
                }
            ) {
                override fun getParams(): HashMap<String, String> {
                    val params = HashMap<String, String>()
                    params["id_member"] = currentTransaction.id_member
                    params["id_buku"] = currentTransaction.id_buku
                    return params
                }
            }
            Volley.newRequestQueue(holder.itemView.context).add(stringRequest)

        }

        holder.cancelButton.setOnClickListener {
            val url: String = ApiEndPoint.DELETE_PINJAM
            val stringRequest = object : StringRequest(
                Method.POST, url,
                Response.Listener { response ->
                    Log.d("response delete booking", response)

                    if (response.equals("success")) {
                        Toast.makeText(
                            holder.itemView.context,
                            "Booking Canceled",
                            Toast.LENGTH_SHORT
                        ).show()
                        bookingCommunicator.cancelBooking(currentTransaction)

                    } else {
                        Toast.makeText(
                            holder.itemView.context,
                            "Failed to cancel booking. Please try again later",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                Response.ErrorListener { response ->
                    Log.d("response", response.toString())
                }
            ) {
                override fun getParams(): HashMap<String, String> {
                    val params = HashMap<String, String>()
                    params["id_member"] = currentTransaction.id_member
                    params["id_buku"] = currentTransaction.id_buku
                    return params
                }
            }
            Volley.newRequestQueue(holder.itemView.context).add(stringRequest)
        }

        holder.cardview.setOnClickListener {
            //pindah fragment atau tampilkan detail
            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle(currentTransaction.id_member + " " + currentTransaction.id_buku)
                .setMessage(
                    "Judul Buku: \t" + currentTransaction.judul_buku
                            + "\nPeminjam: \t" + currentTransaction.nama_member
                            + "\nPinjam: \t" + currentTransaction.tgl_peminjaman
                            + "\nKembali: \t" + currentTransaction.tgl_pengembalian
                            + "\nStatus: \t" + currentTransaction.statusToText(currentTransaction.status)
                )

                .setNegativeButton("Close") { dialog, which ->
                    // Handle the negative button click (Cancel)
                }
                .show()
        }

    }

    fun updateData(newList: List<Pinjam>) {
        TransactionList = newList
        notifyDataSetChanged()
    }
}