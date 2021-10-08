package com.example.problem2


import android.content.ContentValues
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.*
import androidx.lifecycle.lifecycleScope
import com.example.problem2.foundation.BaseFragment
import com.example.problem2.sqllite.DbManager
import com.example.problem2.utils.applyFilter
import com.example.problem2.utils.isValidEmail
import kotlinx.android.synthetic.main.fragment_post.*
import com.example.problem2.view_model.ProjectViewModel
import kotlinx.coroutines.launch


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
            if (inputEmail.text.toString().isValidEmail()) {
                loading()
                lifecycleScope.launch {
                    projectViewModel.publish(
                        inputEmail.text.toString(),
                        publishType,
                        if (isJoke) 1 else 0,
                        description.text.toString()
                    )
                }
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
                Handler(Looper.getMainLooper()).post{
                    val dbManager = DbManager(requireContext())
                    val values = ContentValues()
                    values.put(DbManager.EMAIL, inputEmail.text.toString())
                    values.put(DbManager.PUBLISHER_TYPE, publishType)
                    values.put(DbManager.IS_JOKE, if (isJoke) 1 else 0)
                    values.put(DbManager.DESCRIPTION, description.text.toString())
                    dbManager.insert(values)
                }
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
