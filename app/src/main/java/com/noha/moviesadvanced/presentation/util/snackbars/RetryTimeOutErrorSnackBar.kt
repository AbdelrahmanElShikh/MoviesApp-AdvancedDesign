package com.noha.moviesadvanced.presentation.util.snackbars

import android.content.Context
import androidx.fragment.app.Fragment
import com.noha.moviesadvanced.R
import com.noha.moviesadvanced.presentation.util.errorhandler.RetryHandler


/**
 *@author: Abdel-Rahman El-Shikh on 09-May-20.
 */
class RetryTimeOutErrorSnackBar {
    companion object{
        fun show(retryHandler: RetryHandler?, fragment: Context){
            RetrySnackBar.show(
                retryHandler,
                fragment,
                R.string.error_connection_timeout
            )
        }
    }
}