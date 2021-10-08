package com.example.problem2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.problem2.R
import com.example.problem2.model.Post
import kotlin.collections.ArrayList

class PostAdapter(
    context:Context,
    resId:Int,
    private val postsList: ArrayList<Post>) : ArrayAdapter<Post>(context, resId, postsList) {

    val inf = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.post_list_item, parent,false)
        }
        val row = tempRow!!


        val title = row.findViewById<TextView>(R.id.title)
        val body = row.findViewById<TextView>(R.id.description)

        val post = postsList[position]

        title.text = post.title
        body.text = post.body


        return row
    }

}