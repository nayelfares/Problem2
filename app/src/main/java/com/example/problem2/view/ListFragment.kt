package com.example.problem2.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.problem2.R
import com.example.problem2.foundation.BaseFragment
import com.example.problem2.network.model.Post
import kotlinx.android.synthetic.main.fragment_list.*
import java.util.ArrayList


class ListFragment : BaseFragment(R.layout.fragment_list) {
    var posts:ArrayList<Post>?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        posts = arguments?.getParcelableArrayList("posts")
        if (posts!=null)
            showMessage(posts!!.size.toString())
        button_two.setOnClickListener {
            findNavController().navigate(R.id.action_twoFragment_to_oneFragment)
        }
    }

}


