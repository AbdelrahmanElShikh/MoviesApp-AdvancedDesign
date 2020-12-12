package com.noha.moviesadvanced.framework.di

import com.aib.mobile.banking.authmanager.remote.model.AuthRemote
import com.noha.moviesadvanced.core.data.models.ActorResponseWrapper
import com.noha.moviesadvanced.core.data.models.MoviesResponseWrapper
import com.noha.moviesadvanced.core.mapper.Mapper
import com.noha.moviesadvanced.core.mapper.RemoteResourceMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author : Abdel-Rahman El-Shikh on Sun , 11/22/2020
 */
@Module
class MapperModule{

    @Provides
    fun moviesMapper(): RemoteResourceMapper<MoviesResponseWrapper> {
        return RemoteResourceMapper()
    }

    @Provides
    fun movieActorMapper(): RemoteResourceMapper<ActorResponseWrapper> {
        return RemoteResourceMapper()
    }

    @Provides
    fun tokenMapper(): RemoteResourceMapper<AuthRemote> {
        return RemoteResourceMapper()
    }


}