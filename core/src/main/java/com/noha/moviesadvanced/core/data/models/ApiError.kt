package com.noha.moviesadvanced.core.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 *@author: Abdel-Rahman El-Shikh on 09-May-20.
 */
class ApiError(
    @SerializedName("status_message") @Expose val apiErrorMessage : String?,
    @SerializedName("status_code") @Expose val apiErrorCode : Int?,
    val extras : Map<String,Any>?
)