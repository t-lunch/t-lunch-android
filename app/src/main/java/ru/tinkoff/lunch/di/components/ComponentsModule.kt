package ru.tinkoff.lunch.di.components

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.tinkoff.lunch.network.api.auth.repository.AuthRepository
import ru.tinkoff.lunch.network.common.token.JwtTokenManager
import ru.tinkoff.lunch.root.di.EntrypointComponent
import ru.tinkoff.lunch.root.di.EntrypointModule
import ru.tinkoff.lunch.root.sign_in.di.SignInComponent
import ru.tinkoff.lunch.root.sign_in.di.SignInModule
import ru.tinkoff.lunch.root.sign_up.di.SignUpComponent
import ru.tinkoff.lunch.root.sign_up.di.SignUpModule
import ru.tinkoff.lunch.screens.main.di.MainComponent
import ru.tinkoff.lunch.screens.main.di.MainModule
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ComponentsModule {

    @Provides
    @Singleton
    fun provideEntrypointComponent(
        authRepository: AuthRepository,
    ): EntrypointComponent {
        return object : EntrypointComponent(),
            EntrypointModule by EntrypointModule(
                authRepository = authRepository,
            ) {}
    }

    @Provides
    @Singleton
    fun provideSignInComponent(): SignInComponent {
        return object : SignInComponent(),
            SignInModule by SignInModule() {}
    }

    @Provides
    @Singleton
    fun provideSignUpComponent(
        authRepository: AuthRepository,
        tokenManager: JwtTokenManager,
    ): SignUpComponent {
        return object : SignUpComponent(),
            SignUpModule by SignUpModule(
                authRepository = authRepository,
                tokenManager = tokenManager,
            ) {}
    }

    @Provides
    @Singleton
    fun provideMainComponent(): MainComponent {
        return object : MainComponent(),
            MainModule by MainModule() {}
    }
}
