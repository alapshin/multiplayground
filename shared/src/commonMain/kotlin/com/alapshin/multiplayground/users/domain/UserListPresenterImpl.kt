package com.alapshin.multiplayground.users.domain

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.alapshin.multiplayground.coroutines.DispatcherProvider
import com.alapshin.multiplayground.coroutines.suspendCatching
import com.alapshin.multiplayground.login.data.LoginManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import me.tatarka.inject.annotations.Inject

@Inject
class UserListPresenterImpl(
    private val loginManager: LoginManager,
    private val dispatcherProvider: DispatcherProvider,
) : UserListPresenter {
    @Composable
    override fun state(events: Flow<UserListMvi.Event>): UserListMvi.State {
        var email: String? by remember { mutableStateOf(null) }
        var password: String? by remember { mutableStateOf(null) }
        var success: Boolean by remember { mutableStateOf(false) }

        LaunchedEffect(email, password) {
            val e = email
            val p = password
            if (e != null && p != null) {
                withContext(dispatcherProvider.io) {
                    suspendCatching {
                        loginManager.login(e, p)
                    }
                }.onSuccess {
                    success = true
                }.onFailure {
                    success = false
                }
            }
        }

        LaunchedEffect(Unit) {
        }

        return UserListMvi.State(users = listOf())
    }
}
