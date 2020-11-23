package com.noha.moviesadvanced.core.mapper

import com.google.gson.annotations.SerializedName


/**
 *@author: Abdel-Rahman El-Shikh on 08-May-20.
 */
data class ErrorResponse(
    @SerializedName(value = "message")
    val errorMsg:String,
    @SerializedName(value= "errors")
    val extras:HashMap<String, Any>
)