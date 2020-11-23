package com.noha.moviesadvanced.core.repositories

import com.noha.moviesadvanced.core.data.ApiService
import com.noha.moviesadvanced.core.data.models.MoviesResponseWrapper
import com.noha.moviesadvanced.core.datasource.MoviesDataSource
import com.noha.moviesadvanced.core.mapper.RemoteResourceMapper
import com.noha.moviesadvanced.core.resource.PresentationResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author : Abdel-Rahman El-Shikh on Sun , 11/22/2020
 */
class MoviesRepository @Inject constructor(
    private val moviesMapper: RemoteResourceMapper<MoviesResponseWrapper>,
    private val apiService: ApiService
) : MoviesDataSource {
    override suspend fun getMovies(apiKey : String,language:String): Flow<PresentationResource<MoviesResponseWrapper>> {
        return flow{
            val response =apiService.getMovies(apiKey= apiKey, language = language)
            val movies = moviesMapper.map(response)
            emit(movies)
        }
    }
}