package com.noha.moviesadvanced.presentation.util.errorhandler.domain.handlers

import android.content.Context
import androidx.fragment.app.Fragment
import com.noha.moviesadvanced.presentation.util.errorhandler.RetryHandler
import com.noha.moviesadvanced.presentation.util.errorhandler.domain.chains.DomainErrorChainLink
import com.noha.moviesadvanced.presentation.util.snackbars.RetryConnectionErrorSnackBar
import java.net.SocketException
import java.net.UnknownHostException


/**
 *@author: Abdel-Rahman El-Shikh on 25-Apr-20.
 */
class InternetConnectionHandler : DomainErrorChainLink() {

    companion object{
        fun isConnectionThrowable(throwable: Throwable):Boolean{
            return throwable is UnknownHostException || throwable is SocketException
        }
    }

    override fun handleError(
        throwable: Throwable?,
        fragment: Context,
        retryHandler: RetryHandler?
    ): Boolean {
        if(isConnectionThrowable(throwable!!)){

            RetryConnectionErrorSnackBar.show(retryHandler,fragment)
            return true
        }
        return false
    }
}