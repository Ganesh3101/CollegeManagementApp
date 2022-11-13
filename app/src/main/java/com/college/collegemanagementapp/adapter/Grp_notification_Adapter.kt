package com.college.collegemanagementapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.college.collegemanagementapp.DataClasses.CommitteeData
import com.college.collegemanagementapp.DataClasses.GrpNotificationData
import com.college.collegemanagementapp.DataClasses.ProctorsetData
import com.college.collegemanagementapp.DetailNotification
import com.college.collegemanagementapp.GrpNotificationList
import com.college.collegemanagementapp.R

class Grp_notification_Adapter(private val group_notice: ArrayList<GrpNotificationData>) : RecyclerView.Adapter<Grp_notification_Adapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent:ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.grp_notification_list, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val groupdata  = group_notice[position]


        holder.group_notice.text = groupdata.notification_msg

        holder.itemView.setOnClickListener{

            val intent = Intent(holder.itemView.context, DetailNotification::class.java)
            holder.itemView.context.startActivity(intent)

        }

    }


    override fun getItemCount(): Int {
        return group_notice.size

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {

        val group_notice  = itemView.findViewById(R.id.new_notification) as TextView

    }

}