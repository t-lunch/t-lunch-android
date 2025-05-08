package ru.tinkoff.lunch.root.sign_in.presentation.command_handlers

import com.skydoves.sandwich.retrofit.apiMessage
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import ru.tinkoff.lunch.network.api.auth.model.login.LoginBody
import ru.tinkoff.lunch.network.api.auth.repository.AuthRepository
import ru.tinkoff.lunch.network.common.token.JwtTokenManager
import ru.tinkoff.lunch.root.sign_in.presentation.SignInCommand
import ru.tinkoff.lunch.root.sign_in.presentation.SignInCommandResultEvent
import ru.tinkoff.lunch.root.sign_in.presentation.SignInEvent
import ru.tinkoff.lunch.utils.network.getErrorMessage

class SignInCommandHandler(
    private val authRepository: AuthRepository,
    private val tokenManager: JwtTokenManager,
) : SignInCommandsFlowHandler {

    override fun handle(commands: Flow<SignInCommand>): Flow<SignInEvent> {
        return commands.filterIsInstance<SignInCommand.SignIn>().transform {
            delay(350)
            authRepository.login(body = LoginBody(email = it.login, password = it.password))
                .suspendOnSuccess {
                    tokenManager.saveAccessJwt(token = this.data.accessToken)
                    tokenManager.saveRefreshJwt(token = this.data.refreshToken)
                    emit(SignInCommandResultEvent.SignInSuccess)
                }
                .suspendOnFailure {
                    emit(
                        SignInCommandResultEvent.SignInError(
                            message = getErrorMessage(this.apiMessage)
                        )
                    )
                }
        }
    }
}
