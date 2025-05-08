package ru.tinkoff.lunch.root.presentation.command_handlers

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import ru.tinkoff.lunch.network.api.auth.model.login.LoginBody
import ru.tinkoff.lunch.network.api.auth.repository.AuthRepository
import ru.tinkoff.lunch.root.presentation.EntrypointCommand
import ru.tinkoff.lunch.root.presentation.EntrypointCommandResultEvent.AuthenticationFailed
import ru.tinkoff.lunch.root.presentation.EntrypointEvent


internal class AuthenticationCommandHandler(
    private val authRepository: AuthRepository,
) : EntrypointCommandsFlowHandler {

    override fun handle(commands: Flow<EntrypointCommand>): Flow<EntrypointEvent> {
        return commands.filterIsInstance<EntrypointCommand.Authenticate>()
            .transform {
                // todo: trigger here request with userId (getLunches, for example)
//                authRepository.login(body = LoginBody(email = "test@email.com", password = "test-pass"))
//                delay(1000)
                emit(AuthenticationFailed)
            }
    }
}
