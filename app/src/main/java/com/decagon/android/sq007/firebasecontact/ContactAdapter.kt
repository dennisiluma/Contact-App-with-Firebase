package com.decagon.android.sq007.firebasecontact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.decagon.android.sq007.R

class ContactAdapter(private val userList: ArrayList<Contact>) :
    RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_contact, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.firstName.text = currentItem.firstName
        holder.lastName.text = currentItem.lastName
        holder.phoneNumber.text = currentItem.phoneNumber
//        holder.itemView.setOnClickListener { clickLIstner.onItemClicked(position) }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val firstName = itemView.findViewById<TextView>(R.id.tvContactFirstName)
        val lastName = itemView.findViewById<TextView>(R.id.tvContactLastName)
        val phoneNumber = itemView.findViewById<TextView>(R.id.tvContactNumber)
    }
}
