package com.mobile.fairless.features.welcome.register.service

import com.mobile.fairless.features.welcome.models.City
import com.mobile.fairless.features.welcome.models.UserReceive
import com.mobile.fairless.features.welcome.models.UserRegisterResponse
import com.mobile.fairless.features.welcome.register.repository.RegisterRepository

interface RegisterService {

    suspend fun registerUser(userRegisterResponse: UserRegisterResponse): UserReceive
    suspend fun getCities(): List<City>
}

class RegisterServiceImpl(private val registerRepository: RegisterRepository): RegisterService {

    override suspend fun registerUser(userRegisterResponse: UserRegisterResponse): UserReceive {
        return registerRepository.registerUser(userRegisterResponse)
    }

    override suspend fun getCities(): List<City> = registerRepository.getCities()
}
