package com.example.problem2.view


import android.content.ContentValues
import android.os.*
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.problem2.R
import com.example.problem2.foundation.BaseFragment
import com.example.problem2.sqllite.DbManager
import com.example.problem2.utils.*
import kotlinx.android.synthetic.main.fragment_post.*
import com.example.problem2.view_model.ProjectViewModel
import kotlinx.coroutines.launch
import java.lang.Exception


class PostFragment : BaseFragment(R.layout.fragment_post), AdapterView.OnItemSelectedListener {

    private val paths = arrayOf("Blogger", "Teacher")
    private var publishType = paths[0]
    private var isJoke = false
    private var dbManager : DbManager?=null
    private lateinit var projectViewModel : ProjectViewModel

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
            if (it!=null){
                Handler(Looper.getMainLooper()).post{
                    dbManager = DbManager(requireContext())
                    val values = ContentValues()
                    values.put(DbManager.EMAIL, inputEmail.text.toString())
                    values.put(DbManager.PUBLISHER_TYPE, publishType)
                    values.put(DbManager.IS_JOKE, if (isJoke) 1 else 0)
                    values.put(DbManager.DESCRIPTION, description.text.toString())
                    try {
                        dbManager?.insert(values)
                    }catch (e:Exception){
                        Log.e("Exception",e.message.toString())
                    }
                    lifecycleScope.launch {
                        projectViewModel.getList()
                    }
                }
            }else{
                stopLoading()
                showMessage("publish failed")
            }
        }

        projectViewModel.getListResponse.observe(viewLifecycleOwner){
            stopLoading()
            if (it!=null){
                val bundle =  Bundle()
                bundle.putSerializable("posts", it)
                findNavController().navigate(R.id.action_postFragment_to_listFragment,bundle)
            }else{
                showMessage("get List failed")
            }
        }
    }


    // dropdown on item select
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        publishType = paths[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onDestroy() {
        super.onDestroy()
        // close SQLiteDatabase on destroy fragment
        dbManager?.close()
    }
}
