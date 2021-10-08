package com.example.problem2.view


import android.os.Bundle
import android.view.View
import com.example.problem2.R
import com.example.problem2.adapter.PostAdapter
import com.example.problem2.foundation.BaseFragment
import com.example.problem2.model.Post
import kotlinx.android.synthetic.main.fragment_list.*
import java.util.ArrayList


class ListFragment : BaseFragment(R.layout.fragment_list) {
    var posts:ArrayList<Post>?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        posts = arguments?.getParcelableArrayList("posts")
        if (posts!=null) {
            val mAdapter = PostAdapter(requireContext(), R.layout.post_list_item, posts!!)
            postsList.adapter = mAdapter
        }

        back.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStackImmediate()
        }
    }
}


