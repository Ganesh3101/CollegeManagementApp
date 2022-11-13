package com.college.collegemanagementapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.college.collegemanagementapp.DataClasses.ProctorsetData
import com.college.collegemanagementapp.adapter.ProctorListAdapter
import com.college.collegemanagementapp.fragments.ProctorFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_user_input.*

class UserInput : AppCompatActivity() {
    val currentUser : String? = Firebase.auth.currentUser?.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_input)
        val img_checker : ImageView = findViewById(R.id.verify_checker)
        img_checker.visibility = View.INVISIBLE
        val prn_op : TextView = findViewById(R.id.prn_op)
        val prn_btn : Button = findViewById(R.id.prn_ip)

        prn_btn.setOnClickListener {
            val inflater = LayoutInflater.from(this)
            val v = inflater.inflate(R.layout.prn_ip,null)
            val title : EditText = v.findViewById(R.id.new_prn_num)
            val dialog = AlertDialog.Builder(this)
            dialog.setView(v)
            dialog.setPositiveButton("Ok"){
                    dialog,_->

                prn_op.text = title.text
                prn_btn.visibility = View.INVISIBLE

                val ref  = FirebaseDatabase.getInstance().getReference("Users")
                ref.child("Google").child(currentUser.toString()).child("prn_number").setValue(title.text.toString())
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                checker()
                img_checker.visibility=View.VISIBLE
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

    private fun checker() {
        val img_checker : ImageView = findViewById(R.id.verify_checker)
        val ref  = FirebaseDatabase.getInstance().getReference("Users").child("Google").child(currentUser.toString())
        ref.addValueEventListener(object  : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val check = snapshot.child("verify_checker").getValue()
                if(check==1){
                    img_checker.setImageResource(R.drawable.verfied)
                }
                else{
                    img_checker.setImageResource(R.drawable.reload_loading_process_blue_round_512)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}