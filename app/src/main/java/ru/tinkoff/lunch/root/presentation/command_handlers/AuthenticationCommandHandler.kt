package ru.tinkoff.lunch.root.presentation.command_handlers

import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import ru.tinkoff.lunch.network.api.auth.model.refresh.RefreshBody
import ru.tinkoff.lunch.network.api.auth.repository.AuthRepository
import ru.tinkoff.lunch.network.common.token.JwtTokenManager
import ru.tinkoff.lunch.root.presentation.EntrypointCommand
import ru.tinkoff.lunch.root.presentation.EntrypointCommandResultEvent
import ru.tinkoff.lunch.root.presentation.EntrypointEvent

internal class AuthenticationCommandHandler(
    private val authRepository: AuthRepository,
    private val tokenManager: JwtTokenManager
) : EntrypointCommandsFlowHandler {

    override fun handle(commands: Flow<EntrypointCommand>): Flow<EntrypointEvent> {
        return commands.filterIsInstance<EntrypointCommand.Authenticate>()
            .transform {
                authRepository.refresh(RefreshBody(refreshToken = tokenManager.getRefreshJwt()))
                    .suspendOnSuccess {
                        tokenManager.saveAccessJwt(token = this.data.accessToken)
                        emit(EntrypointCommandResultEvent.AuthenticationPassed)
                    }
                    .suspendOnFailure {
                        emit(EntrypointCommandResultEvent.AuthenticationFailed)
                    }
            }
    }
}
