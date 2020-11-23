package com.noha.moviesadvanced.framework.di

import com.noha.moviesadvanced.framework.MoviesViewModel
import dagger.Component

/**
 * @author : Abdel-Rahman El-Shikh on Sun , 11/22/2020
 */

@Component(modules = [ApplicationModule::class, RepositoryModule::class, RemoteModule::class , MapperModule::class , UseCaseModule::class])
interface ViewModelComponent {
    fun inject(moviesViewModel: MoviesViewModel)
}