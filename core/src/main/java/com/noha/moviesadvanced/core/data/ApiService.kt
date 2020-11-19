package com.noha.moviesadvanced.core.data

import com.noha.moviesadvanced.core.data.models.MoviesResponseWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author : Abdel-Rahman El-Shikh on Thu , 11/19/2020
 */
interface ApiService {
    @GET("movie/popular")
    suspend fun getMovies(@Query("api_key") apiKey: String): Response<MoviesResponseWrapper>
}