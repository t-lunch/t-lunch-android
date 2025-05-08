package ru.tinkoff.lunch.root.sign_up.presentation.command_handlers

import com.skydoves.sandwich.retrofit.apiMessage
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import ru.tinkoff.lunch.network.api.auth.repository.AuthRepository
import ru.tinkoff.lunch.network.common.token.JwtTokenManager
import ru.tinkoff.lunch.root.sign_up.presentation.SignUpCommand
import ru.tinkoff.lunch.root.sign_up.presentation.SignUpCommandResultEvent
import ru.tinkoff.lunch.root.sign_up.presentation.SignUpEvent
import ru.tinkoff.lunch.utils.network.getErrorMessage

class SignUpCommandHandler(
    private val authRepository: AuthRepository,
    private val tokenManager: JwtTokenManager,
) : SignUpCommandsFlowHandler {

    override fun handle(commands: Flow<SignUpCommand>): Flow<SignUpEvent> {
        return commands.filterIsInstance<SignUpCommand.SignUp>().transform { command ->
            delay(1500)
            authRepository.signUp(body = command.signUpBody)
                .suspendOnSuccess {
                    with(this.data) {
                        tokenManager.saveAccessJwt(token = "some access token") // todo
                        tokenManager.saveRefreshJwt(token = "some refresh token") // todo
                        // todo: save user id for future requests inside UserInfoManager

                        emit(SignUpCommandResultEvent.SignUpSuccess)
                    }
                }
                .suspendOnFailure {
                    val message = getErrorMessage(this.apiMessage)
                    emit(SignUpCommandResultEvent.SignUpError(message = message))
                }
        }
    }
}
