package com.noha.moviesadvanced.core.resource

import com.noha.moviesadvanced.core.data.models.ApiError


/**
 *@author: Abdel-Rahman El-Shikh on 09-May-20.
 */
class PresentationResource<T>(
    val status: Status,
    val data: T?,
    val apiError: ApiError?,
    val throwable: Throwable?
) {
    constructor(
        status: Status,
        data: T?,
        errorMsg: String?,
        responseCode: Int,
        errorExtras: Map<String, Any>?,
        throwable: Throwable?
    ) : this(status, data,
        ApiError(
            errorMsg,
            responseCode,
            errorExtras
        ), throwable)

    companion object{
        fun <T> success (data : T): PresentationResource<T> {
            return PresentationResource(
                Status.SUCCESS,
                data,
                null,
                0,
                null,
                null
            )
        }

        fun <T> apiError(errorMsg: String?,responseCode: Int,errorExtras: Map<String, Any>?): PresentationResource<T> {
            return PresentationResource(
                Status.API_ERROR,
                null,
                errorMsg,
                responseCode,
                errorExtras,
                null
            )
        }

        fun <T> domainError(throwable: Throwable?): PresentationResource<T> {
            return PresentationResource(
                Status.DOMAIN_ERROR,
                null,
                null,
                0,
                null,
                throwable
            )
        }

        fun <T> loading(): PresentationResource<T> {
            return PresentationResource(
                Status.LOADING,
                null,
                null,
                0,
                null,
                null
            )
        }
    }
    enum class Status {
        SUCCESS, DOMAIN_ERROR,API_ERROR,LOADING
    }
}