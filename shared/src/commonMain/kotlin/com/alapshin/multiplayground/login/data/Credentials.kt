package com.alapshin.multiplayground.login.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Credentials(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,
)
