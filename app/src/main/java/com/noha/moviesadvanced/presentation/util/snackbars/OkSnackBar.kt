package com.noha.moviesadvanced.presentation.util.snackbars

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.noha.moviesadvanced.R


/**
 *@author: Abdel-Rahman El-Shikh on 14-May-20.
 */
class OkSnackBar {
    companion object {
        fun make(view: View?, text:String, length:Int):Snackbar{
            val snackbar = DynamicSnackBar.make(view, text, length)
            return snackbar.setAction(view!!.context.getString(R.string.ok_snackbar)){
                snackbar.dismiss()
            }
        }
    }
}