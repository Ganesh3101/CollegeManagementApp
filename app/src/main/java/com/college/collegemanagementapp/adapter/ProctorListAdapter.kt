package com.college.collegemanagementapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.college.collegemanagementapp.DataClasses.ProctorsetData
import com.college.collegemanagementapp.R

@Suppress("DEPRECATION")
class ProctorListAdapter(private val verificationlist: ArrayList<ProctorsetData>) : RecyclerView.Adapter<ProctorListAdapter.ViewHolder>()
{
//    private lateinit var mlistner : onItemClickListener
//    interface onItemClickListener{
//        fun onItemClick(position: Int)
//    }
//
//    fun setOnItemClickListener(listener: onItemClickListener){
//        mlistner  = listener
//    }

    override fun onCreateViewHolder(parent:ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.proctorsetlist, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val verificationdata  = verificationlist[position]

        holder.fname.text = verificationdata.g_name_proc
        holder.lname.text = verificationdata.g_surname_proc
        Glide.with(holder.itemView.context).load(verificationdata.g_pic_proc).into(holder.user_img)



    }


    override fun getItemCount(): Int {
        return verificationlist.size

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val fname  = itemView.findViewById(R.id.g_fname) as TextView
        val lname  = itemView.findViewById(R.id.g_lname) as TextView
        val user_img  = itemView.findViewById(R.id.g_img) as ImageView

//        init{
//            itemView.setOnClickListener{
//                listner.onItemClick(adapterPosition)
//            }
//
//        }

    }

}