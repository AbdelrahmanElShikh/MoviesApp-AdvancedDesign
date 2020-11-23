package com.noha.moviesadvanced.core.usecases

import com.noha.moviesadvanced.core.data.models.ActorResponseWrapper
import com.noha.moviesadvanced.core.repositories.ActorsRepository
import com.noha.moviesadvanced.core.resource.PresentationResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author : Abdel-Rahman El-Shikh on Mon , 11/23/2020
 */
class GetMovieActorsUseCase @Inject constructor(
    private val actorsRepository: ActorsRepository
) {
    suspend fun getMovieActors(
        movieId: Int,
        apiKey: String,
        language: String
    ): Flow<PresentationResource<ActorResponseWrapper>> = flow {
        val response = actorsRepository.getMovieActors(movieId, apiKey, language)
        emitAll(response)
    }
}