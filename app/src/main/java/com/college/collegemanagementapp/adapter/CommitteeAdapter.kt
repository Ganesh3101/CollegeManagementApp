package com.college.collegemanagementapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.college.collegemanagementapp.DataClasses.CommitteeData
import com.college.collegemanagementapp.DataClasses.ProctorsetData
import com.college.collegemanagementapp.GrpNotificationList
import com.college.collegemanagementapp.R

@Suppress("DEPRECATION")
class CommitteeAdapter(private val grouplist: ArrayList<CommitteeData>) : RecyclerView.Adapter<CommitteeAdapter.ViewHolder>()
{

    override fun onCreateViewHolder(parent:ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.committeelist, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val groupdata  = grouplist[position]

        holder.group_img.setImageResource(groupdata.committee_img)
        holder.group_name.text = groupdata.committee_name

        holder.itemView.setOnClickListener{

            val intent = Intent(holder.itemView.context, GrpNotificationList::class.java)
            holder.itemView.context.startActivity(intent)

        }


    }


    override fun getItemCount(): Int {
        return grouplist.size

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {

        val group_img  = itemView.findViewById(R.id.grp_img) as ImageView
        val group_name  = itemView.findViewById(R.id.grp_name) as TextView


    }

}