package com.college.collegemanagementapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.college.collegemanagementapp.DataClasses.CommitteeData
import com.college.collegemanagementapp.DataClasses.ProctorsetData
import com.college.collegemanagementapp.GrpNotificationList
import com.college.collegemanagementapp.R
import com.college.collegemanagementapp.adapter.CommitteeAdapter
import com.college.collegemanagementapp.adapter.ProctorListAdapter
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.fragment_committee.*

class CommitteeFragment : Fragment(R.layout.fragment_committee) {

    private lateinit var adapter : CommitteeAdapter
    private lateinit var grplist : ArrayList<CommitteeData>
    private lateinit var recyclerView: RecyclerView

    lateinit var  imageId : Array<Int>
    lateinit var  committee_name : Array<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_committee, container, false)
        return view

    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        recyclerView =  requireView().findViewById(R.id.committee_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)

        Ondatalist()
        adapter = CommitteeAdapter(grplist)
        recyclerView.adapter = adapter


    }

    private fun Ondatalist(){
        grplist = arrayListOf<CommitteeData>()

        imageId = arrayOf(R.drawable.somaiya_logo,
                          R.drawable.somaiya_logo,
                          R.drawable.somaiya_logo,
                          R.drawable.somaiya_logo,
                          R.drawable.somaiya_logo,
                          R.drawable.somaiya_logo,
                          R.drawable.somaiya_logo,
                          R.drawable.somaiya_logo)


        committee_name = arrayOf("CSI","CSI","CSI","CSI","CSI","CSI","CSI","CSI")

        for (i in imageId.indices){
            val list = CommitteeData(imageId[i],committee_name[i])
            grplist.add(list)
        }
    }

}