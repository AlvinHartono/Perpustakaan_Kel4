package com.example.perpustakaan_kel4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewMemberAdapter(var memberList: List<Member>) :
    RecyclerView.Adapter<RecyclerViewMemberAdapter.MyViewHolder>() {

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


    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val memberName: TextView = itemView.findViewById(R.id.MemberNameListView)
        val memberPhoneNumber: TextView = itemView.findViewById(R.id.memberPhoneNumber)
        val cardView: CardView = itemView.findViewById(R.id.MemberCardView)
        val deleteMember: ImageView = itemView.findViewById(R.id.DeleteMemberImg)
        val editMember: ImageView = itemView.findViewById(R.id.EditMemberImg)
    }

}