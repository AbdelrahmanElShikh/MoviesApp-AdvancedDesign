package com.noha.moviesadvanced.core.usecases

import com.aib.mobile.banking.authmanager.model.Auth
import com.aib.mobile.banking.authmanager.remote.model.AuthRemote
import com.aib.mobile.banking.authmanager.repo.AuthRepo
import com.noha.moviesadvanced.core.mapper.RemoteResourceMapper
import com.noha.moviesadvanced.core.resource.PresentationResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 12-Dec-20
 * @Project : com.noha.moviesadvanced.core.usecases
 */
class LoginUseCase @Inject constructor(
    private val authRemoteMapper: RemoteResourceMapper<AuthRemote>,
    private val authRepo : AuthRepo
) {
    suspend fun login(): Flow<PresentationResource<AuthRemote>> = flow {
        val response = authRemoteMapper.map(authRepo.requestAuth("bnmobile7","password"))
        // Saving the AUTH to SharedPreferences
        if(response.status==PresentationResource.Status.SUCCESS){
            val auth = Auth(response.data!!.accessToken,response.data.refreshToken,response.data.tokenType)
            authRepo.auth = auth
        }
        emit(response)
    }

    suspend fun getAuth(): Flow<Auth> = flow {
        val auth = authRepo.auth
        emit(auth)
    }
}