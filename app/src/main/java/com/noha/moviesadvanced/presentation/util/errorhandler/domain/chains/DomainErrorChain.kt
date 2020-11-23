package com.noha.moviesadvanced.presentation.util.errorhandler.domain.chains

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import com.noha.moviesadvanced.presentation.util.errorhandler.RetryHandler


/**
 *@author: Abdel-Rahman El-Shikh on 25-Apr-20.
 */
class DomainErrorChain(val chainHead: DomainErrorChainLink) {
    fun execute(throwable: Throwable?,fragment: Context,retryHandler: RetryHandler?){
        chainHead.proceed(throwable,fragment,retryHandler)
    }

    companion object{
        fun BUILDER(): Builder {
            return Builder()
        }
    }
}