package com.example.newsapplication.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Context.showToast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showSnakBar(message: String){
    view?.let {
        Snackbar.make(view!!,message,Snackbar.LENGTH_LONG).show()
    }
}