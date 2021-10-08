package com.example.problem2.foundation

import android.app.ActionBar
import android.app.Dialog
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.problem2.R

open class BaseFragment(layout:Int):Fragment(layout){
    var loadingDialog:Dialog?=null


    fun loading(){
        loadingDialog = Dialog(requireContext(), R.style.Theme_Design_BottomSheetDialog)
        loadingDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        loadingDialog!!.setCancelable(true)
        loadingDialog!!.setContentView(R.layout.loading)
        val window: Window = loadingDialog!!.window!!
        window.setLayout(
            ActionBar.LayoutParams.MATCH_PARENT,
            ActionBar.LayoutParams.MATCH_PARENT
        )
        if (!requireActivity().isFinishing) {
            loadingDialog?.show()
        }

    }
    fun stopLoading(){
        if (!requireActivity().isFinishing) loadingDialog?.dismiss()
    }


    fun showMessage(message:String){
        Toast.makeText(requireContext(),message, Toast.LENGTH_LONG).show()
    }
    
}
