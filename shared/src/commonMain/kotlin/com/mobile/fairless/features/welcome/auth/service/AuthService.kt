package com.mobile.fairless.features.welcome.auth.service

import com.mobile.fairless.features.welcome.dto.UserReceive
import com.mobile.fairless.features.welcome.dto.UserAuthResponse
import com.mobile.fairless.features.welcome.auth.repository.AuthRepository

interface AuthService {

    suspend fun authUser(userAuthResponse: UserAuthResponse): UserReceive
}

class AuthServiceImpl(private val authRepository: AuthRepository): AuthService {

    override suspend fun authUser(userAuthResponse: UserAuthResponse): UserReceive = authRepository.authUser(userAuthResponse)
}
