package com.mobile.fairless.features.welcome.auth.service

import com.mobile.fairless.features.welcome.auth.dto.UserReceive
import com.mobile.fairless.features.welcome.auth.dto.UserResponse
import com.mobile.fairless.features.welcome.auth.repository.AuthRepository
import com.mobile.fairless.features.welcome.auth.state.AuthState

interface AuthService {
    suspend fun authUser(userResponse: UserResponse): UserReceive
}

class AuthServiceImpl(private val authRepository: AuthRepository): AuthService {

    override suspend fun authUser(userResponse: UserResponse): UserReceive = authRepository.authUser(userResponse)
}
