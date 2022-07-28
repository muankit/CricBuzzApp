package com.cricbuzzapp.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    try {
        val imm: InputMethodManager =
            (applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}