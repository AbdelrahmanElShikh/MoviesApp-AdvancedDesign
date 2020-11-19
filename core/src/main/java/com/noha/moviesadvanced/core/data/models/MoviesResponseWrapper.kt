package com.noha.moviesadvanced.core.data.models

import com.google.gson.annotations.SerializedName

/**
 * @author : Abdel-Rahman El-Shikh on Thu , 11/19/2020
 */
data class MoviesResponseWrapper (
    val page:Int,
    @SerializedName("total_results")val totalResults: Int,
    @SerializedName("total_pages")val totalPages : Int,
    val results : List<Movie>
)