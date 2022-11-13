package com.college.collegemanagementapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.college.collegemanagementapp.DataClasses.GrpNotificationData
import com.college.collegemanagementapp.adapter.Grp_notification_Adapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_grp_notification_list.*

class GrpNotificationList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grp_notification_list)
        val addbtn : Button = findViewById(R.id.add_btn)
        val recyclerview = findViewById<RecyclerView>(R.id.grp_notification_list_recyclerview)

        recyclerview.layoutManager = LinearLayoutManager(this)

        val list = ArrayList<GrpNotificationData>()
        for (i in 1..20){
            list.add(GrpNotificationData("Final MERIT LIST FOR CAP VACANCY SEATS (Non-Minority) 2022-23 for Counseling Round-1 as on 9-11-22"))
        }
        val adapter = Grp_notification_Adapter(list)

        recyclerview.adapter = adapter
       // val currentUser : String? = Firebase.auth.currentUser?.uid



        addbtn.setOnClickListener {
                            val inflater = LayoutInflater.from(this)
                            val v = inflater.inflate(R.layout.add_new_notification,null)
                            val title : EditText = v.findViewById(R.id.add_new_title)

                            val dialog = AlertDialog.Builder(this)
                            dialog.setView(v)
                            dialog.setPositiveButton("Ok"){
                                    dialog,_->
                                val title = title.text.toString()

                                list.add(GrpNotificationData("$title"))
                                adapter.notifyDataSetChanged()

                                dialog.dismiss()
                            }
                            dialog.setNegativeButton("Cancel"){
                                    dialog,_->
                                dialog.dismiss()
                                // Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show()

                            }
                            dialog.create()
                            dialog.show()
                        }
    }

}




