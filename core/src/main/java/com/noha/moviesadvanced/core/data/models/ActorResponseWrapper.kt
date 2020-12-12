package com.noha.moviesadvanced.core.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author : Abdel-Rahman El-Shikh on Mon , 11/23/2020
 */
data class ActorResponseWrapper(
    @SerializedName("cast") @Expose val actors: List<Actor>
)