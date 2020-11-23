package com.noha.moviesadvanced.core.repositories

import com.noha.moviesadvanced.core.data.ApiService
import com.noha.moviesadvanced.core.data.models.ActorResponseWrapper
import com.noha.moviesadvanced.core.datasource.ActorsDataSource
import com.noha.moviesadvanced.core.mapper.RemoteResourceMapper
import com.noha.moviesadvanced.core.resource.PresentationResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author : Abdel-Rahman El-Shikh on Mon , 11/23/2020
 */
class ActorsRepository @Inject constructor(
    private val actorsMapper: RemoteResourceMapper<ActorResponseWrapper>,
    private val apiService: ApiService
):ActorsDataSource{
    override suspend fun getMovieActors(
        movieId: Int,
        apiKey: String,
        language: String
    ): Flow<PresentationResource<ActorResponseWrapper>> = flow{
        val response = apiService.getMovieActors(movieId = movieId,apiKey = apiKey,language = language)
        val actors = actorsMapper.map(response)
        emit(actors)
    }

}