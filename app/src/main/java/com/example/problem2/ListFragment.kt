package com.example.problem2


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment(R.layout.fragment_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_two.setOnClickListener {
            findNavController().navigate(R.id.action_twoFragment_to_oneFragment)
        }
    }

}


