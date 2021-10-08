package com.example.problem2


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AdapterView
import com.example.problem2.utils.applyFilter
import kotlinx.android.synthetic.main.fragment_post.*
import android.widget.ArrayAdapter






class PostFragment : Fragment(R.layout.fragment_post), AdapterView.OnItemSelectedListener {

    private val paths = arrayOf("Blogger", "Teacher")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        description.applyFilter()

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item, paths
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        publisherType.adapter = adapter
        publisherType.setOnItemSelectedListener(this)
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (position) {
            0 -> {
            }
            1 -> {
            }
            2 -> {
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }



}
