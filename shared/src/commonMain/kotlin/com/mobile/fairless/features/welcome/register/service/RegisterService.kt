package com.mobile.fairless.features.welcome.register.service

import com.mobile.fairless.features.welcome.dto.UserReceive
import com.mobile.fairless.features.welcome.dto.UserRegisterResponse
import com.mobile.fairless.features.welcome.register.repository.RegisterRepository

interface RegisterService {

    suspend fun registerUser(userRegisterResponse: UserRegisterResponse): UserReceive
}

class RegisterServiceImpl(private val registerRepository: RegisterRepository): RegisterService {

    override suspend fun registerUser(userRegisterResponse: UserRegisterResponse): UserReceive {
        return registerRepository.registerUser(userRegisterResponse)
    }
}
