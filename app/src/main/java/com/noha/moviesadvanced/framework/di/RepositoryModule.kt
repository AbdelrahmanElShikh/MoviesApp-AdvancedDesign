package com.noha.moviesadvanced.framework.di

import android.app.Application
import com.noha.moviesadvanced.core.data.ApiService
import com.noha.moviesadvanced.core.data.models.ActorResponseWrapper
import com.noha.moviesadvanced.core.data.models.MoviesResponseWrapper
import com.noha.moviesadvanced.core.mapper.RemoteResourceMapper
import com.noha.moviesadvanced.core.repositories.ActorsRepository
import com.noha.moviesadvanced.core.repositories.MoviesRepository
import dagger.Module
import dagger.Provides

/**
 * @author : Abdel-Rahman El-Shikh on Sun , 11/22/2020
 */
@Module
class RepositoryModule {
    @Provides
    fun provideMoviesRepository(apiService: ApiService,mapper: RemoteResourceMapper<MoviesResponseWrapper>)
            = MoviesRepository(mapper,apiService)

    @Provides
    fun provideActorsRepository(apiService: ApiService,mapper: RemoteResourceMapper<ActorResponseWrapper>)
            = ActorsRepository(mapper,apiService)
}