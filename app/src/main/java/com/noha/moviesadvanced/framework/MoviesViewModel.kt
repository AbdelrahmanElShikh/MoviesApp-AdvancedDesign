package com.noha.moviesadvanced.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.noha.moviesadvanced.BuildConfig
import com.noha.moviesadvanced.core.data.models.ActorResponseWrapper
import com.noha.moviesadvanced.core.data.models.MoviesResponseWrapper
import com.noha.moviesadvanced.core.resource.PresentationResource
import com.noha.moviesadvanced.core.usecases.GetMovieActorsUseCase
import com.noha.moviesadvanced.core.usecases.GetMoviesUseCase
import com.noha.moviesadvanced.framework.di.ApplicationModule
import com.noha.moviesadvanced.framework.di.DaggerViewModelComponent
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author : Abdel-Rahman El-Shikh
 */
class MoviesViewModel(app: Application) : AndroidViewModel(app) {

    @Inject
    lateinit var getMoviesUseCase: GetMoviesUseCase

    @Inject
    lateinit var getMovieActorsUseCase: GetMovieActorsUseCase

    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(getApplication()))
            .build()
            .inject(this)
    }

    private val _movies = MutableLiveData<PresentationResource<MoviesResponseWrapper>>()
    val movies: LiveData<PresentationResource<MoviesResponseWrapper>>
        get() = _movies

    private val _actors = MutableLiveData<PresentationResource<ActorResponseWrapper>>()
    val actors: LiveData<PresentationResource<ActorResponseWrapper>>
        get() = _actors

    fun getMovies() {
        viewModelScope.launch {
            _movies.value = PresentationResource.loading()
            try {
                //TODO : The Language is not working
                getMoviesUseCase.getMovies(BuildConfig.API_KEY, BuildConfig.LANGUAGE)
                    .onEach {
                        _movies.value = it
                    }
                    .catch {
                        _movies.value = PresentationResource.domainError(it)
                    }
                    .launchIn(viewModelScope)
            } catch (e: Exception) {
                _movies.value = PresentationResource.domainError(e)
            }
        }
    }

    fun getMovieActors(movieId: Int) {
        viewModelScope.launch {
            _actors.value = PresentationResource.loading()
            try {
                getMovieActorsUseCase.getMovieActors(
                    movieId = movieId,
                    apiKey = BuildConfig.API_KEY,
                    language = BuildConfig.LANGUAGE
                )
                    .onEach { _actors.value = it }
                    .catch { _actors.value = PresentationResource.domainError(it) }
                    .launchIn(viewModelScope)
            } catch (e: Exception) {
                _actors.value = PresentationResource.domainError(e)
            }
        }
    }

}