package com.mobile.fairless.features.profile.state

import com.mobile.fairless.features.welcome.dto.UserReceive

data class ProfileState(
    val user: UserReceive? = null,
    val selectButton: ProfileButton? = null
)

enum class ProfileButton {
    LOGIN,
    CITY,
    EMAIL,
    PASSWORD
}
