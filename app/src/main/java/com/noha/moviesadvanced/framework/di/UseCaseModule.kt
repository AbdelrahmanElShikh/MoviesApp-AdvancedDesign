package com.noha.moviesadvanced.framework.di

import com.aib.mobile.banking.authmanager.remote.model.AuthRemote
import com.aib.mobile.banking.authmanager.repo.AuthRepo
import com.noha.moviesadvanced.core.data.ApiService
import com.noha.moviesadvanced.core.data.models.MoviesResponseWrapper
import com.noha.moviesadvanced.core.mapper.RemoteResourceMapper
import com.noha.moviesadvanced.core.repositories.ActorsRepository
import com.noha.moviesadvanced.core.repositories.MoviesRepository
import com.noha.moviesadvanced.core.usecases.GetMovieActorsUseCase
import com.noha.moviesadvanced.core.usecases.GetMoviesUseCase
import com.noha.moviesadvanced.core.usecases.LoginUseCase
import dagger.Module
import dagger.Provides

/**
 * @author : Abdel-Rahman El-Shikh on Sun , 11/22/2020
 */
@Module
class UseCaseModule {
    @Provides
    fun provideGetMoviesUseCase(moviesRepository: MoviesRepository):GetMoviesUseCase
            = GetMoviesUseCase(moviesRepository)

    @Provides
    fun provideGetMovieActorsUseCase(actorsRepository: ActorsRepository):GetMovieActorsUseCase
            = GetMovieActorsUseCase(actorsRepository)

    @Provides
    fun provideLoginUseCase(actorsMapper: RemoteResourceMapper<AuthRemote> , authRepo : AuthRepo):LoginUseCase
            = LoginUseCase(actorsMapper,authRepo)
}