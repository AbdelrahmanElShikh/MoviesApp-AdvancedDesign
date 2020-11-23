package com.noha.moviesadvanced.core.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author : Abdel-Rahman El-Shikh on Thu , 11/19/2020
 */
data class MoviesResponseWrapper(
    @SerializedName("page") @Expose val page: Int,
    @SerializedName("total_results") @Expose val totalResults: Int,
    @SerializedName("total_pages") @Expose val totalPages: Int,
    @SerializedName("results") @Expose val results: List<Movie>
)