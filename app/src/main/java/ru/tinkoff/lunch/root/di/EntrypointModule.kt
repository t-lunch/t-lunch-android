package ru.tinkoff.lunch.root.di

import ru.tinkoff.kotea.core.KoteaStore
import ru.tinkoff.lunch.root.presentation.EntrypointCommand
import ru.tinkoff.lunch.root.presentation.EntrypointState
import ru.tinkoff.lunch.root.presentation.EntrypointStore
import ru.tinkoff.lunch.root.presentation.EntrypointUpdate
import ru.tinkoff.lunch.root.presentation.command_handlers.AuthenticationCommandHandler

internal interface EntrypointModule {

    fun getEntrypointStore(): EntrypointStore
}

internal fun EntrypointModule(
//    profileRepository: ProfileRepository,
//    userInfoManager: UserInfoManager,
//    promptsRepository: PromptsRepository,
) = object : EntrypointModule {
    override fun getEntrypointStore(): EntrypointStore {
        return KoteaStore(
            initialState = EntrypointState(),
            initialCommands = listOf(EntrypointCommand.Authenticate),
            commandsFlowHandlers = listOf(
                AuthenticationCommandHandler(
//                    profileRepository = profileRepository,
//                    userInfoManager = userInfoManager
                ),
//                GetPromptsCommandHandler(
//                    userInfoManager = userInfoManager,
//                    promptsRepository = promptsRepository,
//                ),
            ),
            update = EntrypointUpdate(),
        )
    }
}
