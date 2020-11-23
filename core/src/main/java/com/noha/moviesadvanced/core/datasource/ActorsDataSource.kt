package com.noha.moviesadvanced.core.datasource

import com.noha.moviesadvanced.core.data.models.ActorResponseWrapper
import com.noha.moviesadvanced.core.resource.PresentationResource
import kotlinx.coroutines.flow.Flow

/**
 * @author : Abdel-Rahman El-Shikh on Mon , 11/23/2020
 */
interface ActorsDataSource {
    suspend fun getMovieActors(movieId : Int ,apiKey : String,language: String) : Flow<PresentationResource<ActorResponseWrapper>>
}