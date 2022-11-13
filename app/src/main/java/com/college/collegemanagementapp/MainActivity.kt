package com.college.collegemanagementapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.college.collegemanagementapp.fragments.CommitteeFragment
import com.college.collegemanagementapp.fragments.HomeFragment
import com.college.collegemanagementapp.fragments.ProctorFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

//const val TOPIC = "/topics/myTopics"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val home = HomeFragment()
        val group = CommitteeFragment()
        val bell = ProctorFragment()

        setCurrentFragment(home)

        var nav : BottomNavigationView = findViewById(R.id.bottomNavigationView)

        nav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home->setCurrentFragment(home)
                R.id.group->setCurrentFragment(group)
                R.id.bell->setCurrentFragment(bell)
            }
            true
        }
    }
    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout,fragment)
            commit()
        }

//        for(user in firebaseAuth.currentUser!!.providerData){
//            if(user.providerId == "google.com"){
//                val currentUser : String? = Firebase.auth.currentUser?.uid
//                val g_ref = FirebaseDatabase.getInstance().getReference("Users").child("Google").child(currentUser.toString())
//
//                g_ref.addValueEventListener(object : ValueEventListener {
//                    override fun onDataChange(snapshot: DataSnapshot) {
//                        if (snapshot.exists()) {
//                            var currentUser_token = snapshot.child("user_token").getValue()
//
//                            main_token = currentUser_token.toString()
//                        }
//                    }
//
//                    override fun onCancelled(error: DatabaseError) {
//                        TODO("Not yet implemented")
//                    }
//                })
//            }
//        }
//
//        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
//
//
//        var SendBtn : Button = findViewById<Button>(R.id.btnSend)
//        var Title : EditText = findViewById<EditText>(R.id.etTitle)
//        var Message : EditText = findViewById<EditText>(R.id.etMessage)
//
//
//            SendBtn.setOnClickListener {
//            val title = Title.text.toString()
//            val message = Message.text.toString()
//            if(title.isNotEmpty() && message.isNotEmpty()) {
//                PushNotificationsData(
//                    NotificationData(title, message),
//                    main_token
//                ).also {
//                    sendNotification(it)
//                }
//            }
//        }
//    }
//
//    private fun sendNotification(notification: PushNotificationsData) = CoroutineScope(Dispatchers.IO).launch {
//        try {
//            val response = RetrofitInstance.api.postNotification(notification)
//            if(response.isSuccessful) {
//                Log.d(TAG, "Response: ${Gson().toJson(response)}")
//            } else {
//                Log.e(TAG, response.errorBody().toString())
//            }
//        } catch(e: Exception) {
//            Log.e(TAG, e.toString())
//        }
//    }
}

