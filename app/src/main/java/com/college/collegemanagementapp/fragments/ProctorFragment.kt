package com.college.collegemanagementapp.fragments


import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.college.collegemanagementapp.DataClasses.GrpNotificationData
//import com.college.collegemanagementapp.DataClasses.NotificationRecyclerviewData
import com.college.collegemanagementapp.DataClasses.ProctorsetData
import com.college.collegemanagementapp.R
import com.college.collegemanagementapp.adapter.ProctorListAdapter
//import com.college.collegemanagementapp.adapter.notification_adapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_user_input.*

class ProctorFragment : Fragment(R.layout.fragment_proctor) {
    private lateinit var adapter: ProctorListAdapter
    private lateinit var userlist : ArrayList<ProctorsetData>
    private lateinit var db_ref : DatabaseReference
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_proctor, container, false)

        return view
    }


    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

            recyclerView =  requireView().findViewById(R.id.proctor_recyclerview)
            recyclerView.layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
            getData()

//        adapter.setOnItemClickListener(object : ProctorListAdapter.onItemClickListener{
//            override fun onItemClick(position: Int) {
//                val inflater = LayoutInflater.from(activity)
//                val v = inflater.inflate(R.layout.prn_ip,null)
//                val title : EditText = v.findViewById(R.id.new_prn_num)
//                val dialog = AlertDialog.Builder(requireActivity())
//                dialog.setView(v)
//                val currentUser : String? = Firebase.auth.currentUser?.uid
//                val ref = FirebaseDatabase.getInstance().getReference("Users").child("Gooogle").child(currentUser.toString())
//                ref.addValueEventListener(object : ValueEventListener {
//                    override fun onDataChange(snapshot: DataSnapshot) {
//                        if (snapshot.exists()) {
//                            var prn_num = snapshot.child("prn_number").getValue()
//                            title.text = prn_num as Editable?
//
//                        }
//                    }
//
//                    override fun onCancelled(error: DatabaseError) {
//                        TODO("Not yet implemented")
//                    }
//                })
//                dialog.setPositiveButton("Ok"){
//                        dialog,_->
//                    val ref  = FirebaseDatabase.getInstance().getReference("Users")
//                    ref.child("Google").child(currentUser.toString()).child("prn_number").setValue(title.text.toString())
//                    ref.child("Google").child(currentUser.toString()).child("verify_checker").setValue(1)
//                    dialog.dismiss()
//                }
//                dialog.setNegativeButton("Cancel"){
//                        dialog,_->
//                    dialog.dismiss()
//                    // Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show()
//
//                }
//                dialog.create()
//                dialog.show()
//            }
//
//        })

    }


    private fun getData() {

        userlist = arrayListOf<ProctorsetData>()
        db_ref = FirebaseDatabase.getInstance().getReference("Proctor_list")

        db_ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapshot in snapshot.children){
                        val user_fname = userSnapshot.child("g_name_proc").getValue()
                        val user_lname = userSnapshot.child("g_surname_proc").getValue()
                        val user_img  = userSnapshot.child("g_pic_proc").getValue()

                        userlist.add(ProctorsetData(user_fname.toString(),user_lname.toString(),user_img.toString(),"0"))
                        adapter = ProctorListAdapter(userlist)

                        recyclerView.adapter = adapter
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

}