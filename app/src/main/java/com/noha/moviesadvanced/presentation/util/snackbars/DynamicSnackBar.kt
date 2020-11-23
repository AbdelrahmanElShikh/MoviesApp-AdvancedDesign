package com.noha.moviesadvanced.presentation.util.snackbars

import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar


/**
 *@author: Abdel-Rahman El-Shikh on 25-Apr-20.
 */
class DynamicSnackBar {
    companion object{
        fun make(view: View?, text:String, length:Int):Snackbar{
            val snackbar = Snackbar.make(view!!,text,length)
            val snackBarView = snackbar.view
            val textView = snackBarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView.maxLines = 5
            return snackbar
        }
    }
}