package com.alapshin.multiplayground.di

import com.alapshin.multiplayground.coroutines.DispatcherProvider
import com.alapshin.multiplayground.coroutines.DispatcherProviderImpl
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
interface DispatcherComponent {
    @Provides
    fun dispatcherProvider(provider: DispatcherProviderImpl): DispatcherProvider = provider
}
