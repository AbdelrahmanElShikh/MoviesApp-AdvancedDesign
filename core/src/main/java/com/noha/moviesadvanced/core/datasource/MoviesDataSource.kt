package com.noha.moviesadvanced.core.datasource

import com.noha.moviesadvanced.core.data.models.MoviesResponseWrapper
import com.noha.moviesadvanced.core.resource.PresentationResource
import kotlinx.coroutines.flow.Flow

/**
 * @author : Abdel-Rahman El-Shikh on Thu , 11/19/2020
 */
interface MoviesDataSource {
    suspend fun getMovies(apiKey : String,language: String):Flow<PresentationResource<MoviesResponseWrapper>>
}