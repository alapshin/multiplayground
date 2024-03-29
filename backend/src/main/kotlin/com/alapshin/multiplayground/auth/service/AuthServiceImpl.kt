package com.alapshin.multiplayground.auth.service

import com.alapshin.multiplayground.auth.data.AuthRepository
import com.alapshin.multiplayground.jwt.JwtCreator
import com.alapshin.multiplayground.user.data.User
import me.tatarka.inject.annotations.Inject

@Inject
class AuthServiceImpl constructor(
    private val jwtCreator: JwtCreator,
    private val repository: AuthRepository,
) : AuthService {
    override fun registerUser(username: String, password: String): User? {
        return repository.registerUser(username, password)
    }

    override fun authenticateUser(username: String, password: String): Pair<User, String>? {
        val user = repository.authenticateUser(username, password)
        return if (user == null) {
            null
        } else {
            Pair(user, jwtCreator.create(user.username))
        }
    }
}
