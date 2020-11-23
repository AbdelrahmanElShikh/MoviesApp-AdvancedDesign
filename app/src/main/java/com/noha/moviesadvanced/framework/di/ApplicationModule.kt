package com.noha.moviesadvanced.framework.di

import android.app.Application
import dagger.Module
import dagger.Provides

/**
 * @author : Abdel-Rahman El-Shikh on Sun , 11/22/2020
 */
@Module
class ApplicationModule(val app: Application) {
    @Provides
    fun providesApp() = app
}