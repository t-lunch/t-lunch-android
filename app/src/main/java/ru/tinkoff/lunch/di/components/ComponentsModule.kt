package ru.tinkoff.lunch.di.components

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
internal class ComponentsModule {

    @Provides
    @Singleton
    fun provideEntrypointComponent(
//        profileRepository: ProfileRepository,
//        userInfoManager: UserInfoManager,
//        promptsRepository: PromptsRepository,
    ): EntrypointComponent {
        return object : EntrypointComponent(),
            EntrypointModule by EntrypointModule(
//                profileRepository,
//                userInfoManager,
//                promptsRepository,
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
    fun provideSignUpComponent(): SignUpComponent {
        return object : SignUpComponent(),
            SignUpModule by SignUpModule() {}
    }

    @Provides
    @Singleton
    fun provideMainComponent(): MainComponent {
        return object : MainComponent(),
            MainModule by MainModule() {}
    }
}
