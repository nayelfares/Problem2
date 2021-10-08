package com.example.problem2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.problem2.R
import com.example.problem2.network.model.Post
import kotlin.collections.ArrayList

class PostAdapter(
    mContext:Context,
    resId:Int,
    val mList: ArrayList<Post>) : ArrayAdapter<Post>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.post_list_item, null)
        }
        val row = tempRow!!


        val title = row.findViewById<TextView>(R.id.title)
        val body = row.findViewById<TextView>(R.id.description)

        val studentData = mList[position]

        title.text = studentData.title
        body.text = studentData.body


        return row
    }

}