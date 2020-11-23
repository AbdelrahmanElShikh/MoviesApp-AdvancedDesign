package com.noha.moviesadvanced.core.data.models


/**
 *@author: Abdel-Rahman El-Shikh on 09-May-20.
 */
class ApiError(
    val status_message : String?,
    val status_code : Int?,
    val extras : Map<String,Any>?
)