package com.noha.moviesadvanced.core.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("title") @Expose val title: String,
    @SerializedName("overview") @Expose val overview: String,
    @SerializedName("poster_path") @Expose val poster: String,
    @SerializedName("vote_average") @Expose val rate: Float,
    @SerializedName("id") @Expose val id: Int
)