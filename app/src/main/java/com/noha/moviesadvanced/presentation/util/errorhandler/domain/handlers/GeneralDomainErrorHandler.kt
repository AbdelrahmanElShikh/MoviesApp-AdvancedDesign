package com.noha.moviesadvanced.presentation.util.errorhandler.domain.handlers

import android.content.Context
import androidx.fragment.app.Fragment
import com.noha.moviesadvanced.presentation.util.errorhandler.RetryHandler
import com.noha.moviesadvanced.presentation.util.errorhandler.domain.chains.DomainErrorChainLink
import com.noha.moviesadvanced.presentation.util.snackbars.RetryGeneralErrorSnackBar


/**
 *@author: Abdel-Rahman El-Shikh on 09-May-20.
 */
class GeneralDomainErrorHandler : DomainErrorChainLink() {
    override fun handleError(
        throwable: Throwable?,
        fragment: Context,
        retryHandler: RetryHandler?
    ): Boolean {
        RetryGeneralErrorSnackBar.show(retryHandler,fragment)
        return true
    }
}