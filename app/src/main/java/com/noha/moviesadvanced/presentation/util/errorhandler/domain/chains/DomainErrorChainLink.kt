package com.noha.moviesadvanced.presentation.util.errorhandler.domain.chains

import android.content.Context
import androidx.fragment.app.Fragment
import com.noha.moviesadvanced.presentation.util.errorhandler.ChainLink
import com.noha.moviesadvanced.presentation.util.errorhandler.RetryHandler


/**
 *@author: Abdel-Rahman El-Shikh on 25-Apr-20.
 */
abstract class DomainErrorChainLink : ChainLink<DomainErrorChainLink>() {
    fun proceed(throwable: Throwable?,fragment: Context,retryHandler: RetryHandler?){
        if(!handleError(throwable = throwable,fragment = fragment,retryHandler = retryHandler))
            nextLink.proceed(throwable,fragment,retryHandler)
    }
    protected abstract fun handleError(throwable: Throwable?,fragment: Context,retryHandler: RetryHandler?):Boolean
}