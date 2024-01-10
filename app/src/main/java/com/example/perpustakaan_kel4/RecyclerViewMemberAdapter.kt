package com.example.perpustakaan_kel4

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RecyclerViewMemberAdapter(var memberList: List<Member>) :
    RecyclerView.Adapter<RecyclerViewMemberAdapter.MyViewHolder>() {

    lateinit var librarianCommunicator: LibrarianCommunicator

    //delete from list
    fun deleteMember(position: Int) {
        val mutableList =memberList.toMutableList()
        mutableList.removeAt(position)
        memberList = mutableList.toList()
        notifyItemRemoved(position)
    }

    fun updateData(newList: List<Member>) {
        memberList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_member, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentMembers = memberList[position]

        holder.memberName.text = currentMembers.first_name_member + " " +  currentMembers.last_name_member
        holder.memberPhoneNumber.text = currentMembers.no_telp
        holder.cardView.setOnClickListener {
            Toast.makeText(holder.itemView.context, currentMembers.id_member, Toast.LENGTH_SHORT)
                .show()
        }
        holder.deleteMember.setOnClickListener {

            Toast.makeText(holder.itemView.context, "deleted", Toast.LENGTH_SHORT)
                .show()
            val url : String = ApiEndPoint.DELETE_MEMBER
            val stringRequest = object : StringRequest(
                Method.POST, url,
                Response.Listener { response ->
                    Log.d("response", response)

                    if (response.equals("success")) {
                        Toast.makeText(
                            holder.itemView.context,
                            "Account Deleted",
                            Toast.LENGTH_SHORT
                        ).show()
                        deleteMember(holder.adapterPosition)

//                        librarianCommunicator.deleteMember(currentMembers.id_member.toInt())

                    } else {
                        Toast.makeText(
                            holder.itemView.context,
                            "Failed to delete account. Please try again later",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                },
                Response.ErrorListener { response ->
                    Log.d("response", response.toString())
                }
            ) {
                override fun getParams(): HashMap<String, String> {
                    val params = HashMap<String, String>()
                    params["id_member"] = currentMembers.id_member
                    return params
                }
            }
            Volley.newRequestQueue(holder.itemView.context).add(stringRequest)


        }
        holder.editMember.setOnClickListener {
            Toast.makeText(holder.itemView.context, "edited", Toast.LENGTH_SHORT)
                .show()
        }



    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val memberName: TextView = itemView.findViewById(R.id.MemberNameListView)
        val memberPhoneNumber: TextView = itemView.findViewById(R.id.memberPhoneNumber)
        val cardView: CardView = itemView.findViewById(R.id.MemberCardView)
        val deleteMember: ImageView = itemView.findViewById(R.id.DeleteMemberImg)
        val editMember: ImageView = itemView.findViewById(R.id.EditMemberImg)
    }

}