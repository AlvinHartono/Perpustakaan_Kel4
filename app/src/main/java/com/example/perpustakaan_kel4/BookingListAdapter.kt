package com.example.perpustakaan_kel4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class BookingListAdapter(context: Context, dataArrayList: ArrayList<Pinjam?>?):
ArrayAdapter<Pinjam?>(context, R.layout.list_booking, dataArrayList!!)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view = convertView
        val listData = getItem(position)

        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.list_booking, parent, false)
        }

        val listImage = view!!.findViewById<ImageView>(R.id.booking_list_img)
        val listJudulBuku = view!!.findViewById<TextView>(R.id.list_judulbuku)
        val listTglpinjam = view!!.findViewById<TextView>(R.id.list_tglpinjam)
        val listStatus = view!!.findViewById<TextView>(R.id.list_status)

        //TODO: Fetch data

        return view
    }
}