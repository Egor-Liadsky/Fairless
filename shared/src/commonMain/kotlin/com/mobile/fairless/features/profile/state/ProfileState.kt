package com.mobile.fairless.features.profile.state

import com.mobile.fairless.features.welcome.models.City
import com.mobile.fairless.features.welcome.models.UserReceive

data class ProfileState(
    val user: UserReceive? = null,
    val selectButton: ProfileButton? = null,

    val cities: List<City>? = null,
    val city: City? = null, //TODO
    val search: String = "",

    val isLoading: Boolean = false,
)

enum class ProfileButton {
    LOGIN,
    CITY,
    EMAIL,
    PASSWORD
}
