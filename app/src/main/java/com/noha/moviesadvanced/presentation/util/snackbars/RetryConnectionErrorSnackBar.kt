package com.noha.moviesadvanced.presentation.util.snackbars

import android.content.Context
import androidx.fragment.app.Fragment
import com.noha.moviesadvanced.R
import com.noha.moviesadvanced.presentation.util.errorhandler.RetryHandler


/**
 *@author: Abdel-Rahman El-Shikh on 25-Apr-20.
 */
class RetryConnectionErrorSnackBar {
    companion object{
        fun show(retryHandler: RetryHandler?, fragment: Context){
            RetrySnackBar.show(retryHandler, fragment, R.string.no_internet_connection_msg)
        }
    }
}