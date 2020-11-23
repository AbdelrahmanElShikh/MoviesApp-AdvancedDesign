package com.noha.moviesadvanced.core.usecases

import com.noha.moviesadvanced.core.data.models.MoviesResponseWrapper
import com.noha.moviesadvanced.core.repositories.MoviesRepository
import com.noha.moviesadvanced.core.resource.PresentationResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @author : Abdel-Rahman El-Shikh on Sun , 11/22/2020
 */
class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend fun getMovies(apiKey : String,language:String): Flow<PresentationResource<MoviesResponseWrapper>> = flow {
        val response = moviesRepository.getMovies(apiKey = apiKey,language = language)
        emitAll(response)
    }
}