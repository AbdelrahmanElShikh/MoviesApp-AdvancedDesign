package com.noha.moviesadvanced.presentation.util.errorhandler.domain.chains

import com.noha.moviesadvanced.presentation.util.errorhandler.domain.handlers.GeneralDomainErrorHandler
import com.noha.moviesadvanced.presentation.util.errorhandler.domain.handlers.InternetConnectionHandler
import com.noha.moviesadvanced.presentation.util.errorhandler.domain.handlers.TimeOutDomainErrorHandler


/**
 *@author: Abdel-Rahman El-Shikh on 25-Apr-20.
 */
class Builder {
    private var domainErrorChainLinks = ArrayList<DomainErrorChainLink>()

    fun addChain(domainErrorChainLink: DomainErrorChainLink): Builder {
        domainErrorChainLinks.add(domainErrorChainLink)
        return this
    }

    fun setUpChainSequence() {
        if (domainErrorChainLinks.size > 0)
            for (i in 0 until (domainErrorChainLinks.size - 1)) {
                if (i < domainErrorChainLinks.size - 1) {
                    val domainErrorChainLink = domainErrorChainLinks[i]
                    domainErrorChainLink.setNextChain(domainErrorChainLinks[i + 1])
                }
            }
    }

    fun build(): DomainErrorChain {
        setUpChainSequence()
        return DomainErrorChain(
            domainErrorChainLinks[0]
        )
    }


    fun buildWithDefaultChainLinks(): DomainErrorChain {
        domainErrorChainLinks.add(InternetConnectionHandler())
        domainErrorChainLinks.add(TimeOutDomainErrorHandler())
        domainErrorChainLinks.add(GeneralDomainErrorHandler())
        build()
        return DomainErrorChain(domainErrorChainLinks[0])
    }
}