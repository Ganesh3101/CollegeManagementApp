package com.college.collegemanagementapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlin.random.Random

class ProfilePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)

        val firebaseAuth = FirebaseAuth.getInstance()

        FirebaseApp.initializeApp(this)

        val prof_img      = findViewById<ImageView>(R.id.profile_img)
        val textViewname = findViewById<TextView>(R.id.profile_name)
        val textViewemail = findViewById<TextView>(R.id.profile_email)

        val log_out_btn  = findViewById<Button>(R.id.log_out)
        log_out_btn.setOnClickListener {
            logout()
        }

        for(user in firebaseAuth.currentUser!!.providerData){
            if(user.providerId == "google.com"){

                val currentUser : String? = Firebase.auth.currentUser?.uid

                val g_ref = FirebaseDatabase.getInstance().getReference("Users").child("Google").child(currentUser.toString())

                g_ref.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if (snapshot.exists()) {
                                var g_name = snapshot.child("g_name").getValue()
                                var g_email = snapshot.child("g_email").getValue()
                                var g_surname = snapshot.child("g_surname").getValue()
                                var g_prof_img = snapshot.child("g_pic").getValue().toString()


                                Picasso.with(this@ProfilePage).load(g_prof_img).into(prof_img)
                                textViewname.text = "$g_name" + " " +  "$g_surname"
                                textViewemail.text = g_email.toString()
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }
                    })




            }
        }

    }
    private fun logout(){
//        Firebase.auth.signOut()
        val intent = Intent(this, UserInput::class.java)
        startActivity(intent)
    }
}