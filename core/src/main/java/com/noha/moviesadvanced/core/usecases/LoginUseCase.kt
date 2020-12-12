package com.noha.moviesadvanced.core.usecases

import com.aib.mobile.banking.authmanager.remote.model.AuthRemote
import com.aib.mobile.banking.authmanager.repo.AuthRepo
import com.noha.moviesadvanced.core.mapper.RemoteResourceMapper
import com.noha.moviesadvanced.core.resource.PresentationResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 12-Dec-20
 * @Project : com.noha.moviesadvanced.core.usecases
 */
class LoginUseCase @Inject constructor(
    private val actorsMapper: RemoteResourceMapper<AuthRemote>,
    private val authRepo : AuthRepo
) {
    suspend fun login(): Flow<PresentationResource<AuthRemote>> = flow {
            val response = actorsMapper.map(authRepo.requestAuth("bnmobile7","password"))
            emit(response)

    }
}