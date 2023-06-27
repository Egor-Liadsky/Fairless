package com.mobile.fairless.features.profile.service

import com.mobile.fairless.features.welcome.models.City
import com.mobile.fairless.features.welcome.register.repository.RegisterRepository

interface ProfileService {

    suspend fun getCities(): List<City>
}

class ProfileServiceImpl(private val registerRepository: RegisterRepository): ProfileService {

    override suspend fun getCities(): List<City> = registerRepository.getCities()
}

