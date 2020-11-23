package com.noha.moviesadvanced.presentation.util.errorhandler.domain.handlers

import android.content.Context
import androidx.fragment.app.Fragment
import com.noha.moviesadvanced.presentation.util.errorhandler.RetryHandler
import com.noha.moviesadvanced.presentation.util.errorhandler.domain.chains.DomainErrorChainLink
import com.noha.moviesadvanced.presentation.util.snackbars.RetryTimeOutErrorSnackBar
import java.net.SocketTimeoutException


/**
 *@author: Abdel-Rahman El-Shikh on 09-May-20.
 */
class TimeOutDomainErrorHandler : DomainErrorChainLink() {
    override fun handleError(
        throwable: Throwable?,
        fragment: Context,
        retryHandler: RetryHandler?
    ): Boolean {
        if (throwable is SocketTimeoutException) {
            RetryTimeOutErrorSnackBar.show(retryHandler, fragment)
            return true
        }
        return false
    }
}