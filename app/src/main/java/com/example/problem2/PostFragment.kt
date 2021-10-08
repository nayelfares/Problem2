package com.example.problem2


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.*
import com.example.problem2.foundation.BaseFragment
import com.example.problem2.utils.applyFilter
import com.example.problem2.utils.isValidEmail
import kotlinx.android.synthetic.main.fragment_post.*
import com.example.problem2.view_model.ProjectViewModel


class PostFragment : BaseFragment(R.layout.fragment_post), AdapterView.OnItemSelectedListener {

    private val paths = arrayOf("Blogger", "Teacher")
    private var publishType = paths[0]
    private var isJoke = false
    lateinit var projectViewModel : ProjectViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //create viewModel
        projectViewModel = ProjectViewModel()

        //Apply filter for TextArea
        description.applyFilter()

        // initialize dropDown list
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item, paths
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        publisherType.adapter = adapter
        publisherType.onItemSelectedListener = this

        //Subscribe publish livedata
        subscribeObserver()

        // CheckBox listener
        isJokeOrNot.setOnCheckedChangeListener { _, isChecked -> isJoke = isChecked }

        //Handling Submit Button
        submit.setOnClickListener {
            loading()
            if (inputEmail.text.toString().isValidEmail()) {
                projectViewModel.publish(
                    inputEmail.text.toString(),
                    publishType,
                    isJoke,
                    description.text.toString()
                )
            }
            else
                showMessage(getString(R.string.email_not_valid))
        }

    }

    private fun subscribeObserver(){
        projectViewModel.publishResponse.observe(viewLifecycleOwner){
            stopLoading()
            if (it!=null){
                showMessage("Success")
            }else{
                showMessage("Failure")
            }
        }
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        publishType = paths[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }



}
