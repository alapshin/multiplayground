package com.alapshin.multiplayground.core

import io.ktor.server.routing.Routing
import me.tatarka.inject.annotations.Inject

@Inject
class ControllerManager constructor(private val routers: Set<Controller>) {
    fun setup(routing: Routing) {
        for (router in routers) {
            router.setup(routing)
        }
    }
}
