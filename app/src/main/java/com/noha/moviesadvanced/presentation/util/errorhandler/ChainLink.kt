package com.noha.moviesadvanced.presentation.util.errorhandler


/**
 *@author: Abdel-Rahman El-Shikh on 25-Apr-20.
 */
abstract class ChainLink<T : ChainLink<T>> {
    protected lateinit var nextLink: T

    fun setNextChain(chainLink: T) {
        this.nextLink = chainLink
    }
}