package com.example.perpustakaan_kel4

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

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
        val cancelBooking: TextView = itemView.findViewById(R.id.cancelButtonBooking)
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

            holder.cancelBooking.isInvisible = true

            holder.btstglkembali.isInvisible = true


        } else {
            holder.status.text = "Belum dikembalikan"
            holder.btstglkembali.text = currentBooking.batas_tgl_pengembalian.toString()

        }
        holder.cancelBooking.setOnClickListener {
            showCancelBookingConfirmationDialog(holder.itemView.context) {
                // Handle cancellation logic here if confirmed
                cancelBooking(currentBooking, holder, position)
            }
        }


    }

    private fun cancelBooking(currentBookings: Pinjam, holder: MyViewHolder, position: Int) {
        val url : String = ApiEndPoint.DELETE_PINJAM
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
//                    deleteMember(holder.adapterPosition)

                    val mutableList = bookings.toMutableList()
                    mutableList.removeAt(position)
                    bookings = mutableList.toList()
                    notifyItemRemoved(position)

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
                params["id_member"] = currentBookings.id_member
                params["id_buku"] = currentBookings.id_buku
                return params
            }
        }
        Volley.newRequestQueue(holder.itemView.context).add(stringRequest)

    }

    private fun showCancelBookingConfirmationDialog(context: Context, onConfirm: () -> Unit) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("Cancel Booking")
        alertDialogBuilder.setMessage("Are you sure you want to cancel this booking?")
        alertDialogBuilder.setNegativeButton("No") { dialog, _ ->
            // Handle negative button click
            dialog.dismiss()
        }
        alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
            // Handle positive button click
            onConfirm.invoke()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

}