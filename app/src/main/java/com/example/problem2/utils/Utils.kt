package com.example.problem2.utils

import android.text.InputFilter
import android.widget.EditText
import java.util.regex.Matcher
import java.util.regex.Pattern

fun isValidEmail(email: String): Boolean {
    val s =
        "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
    val patt: Pattern = Pattern.compile(s)
    val mat: Matcher = patt.matcher(email)
    return mat.matches()
}

fun EditText.applyFilter(){
    val blockCharacterSet = "<>&"

    val filter = InputFilter { source, start, end, dest, dstart, dend ->
        if (source != null && blockCharacterSet.contains("" + source)) {
            ""
        } else null
    }

    this.filters= arrayOf(filter)
}