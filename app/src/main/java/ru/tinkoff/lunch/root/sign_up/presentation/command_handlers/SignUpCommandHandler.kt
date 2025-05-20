package ru.tinkoff.lunch.root.sign_up.presentation.command_handlers

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
import ru.tinkoff.lunch.network.common.user_info.UserInfoManager
import ru.tinkoff.lunch.root.sign_up.presentation.SignUpCommand
import ru.tinkoff.lunch.root.sign_up.presentation.SignUpCommandResultEvent
import ru.tinkoff.lunch.root.sign_up.presentation.SignUpEvent
import ru.tinkoff.lunch.utils.network.getErrorMessage

class SignUpCommandHandler(
    private val authRepository: AuthRepository,
    private val tokenManager: JwtTokenManager,
    private val userInfoManager: UserInfoManager,
) : SignUpCommandsFlowHandler {

    override fun handle(commands: Flow<SignUpCommand>): Flow<SignUpEvent> {
        return commands.filterIsInstance<SignUpCommand.SignUp>().transform { command ->
            delay(350)
            authRepository.signUp(body = command.signUpBody)
                .suspendOnSuccess {
                    userInfoManager.saveUserId(id = this.data.userId)
                    authRepository.login(
                        body = LoginBody(
                            email = command.signUpBody.email,
                            password = command.signUpBody.password
                        )
                    ).suspendOnSuccess {
                        tokenManager.saveAccessJwt(token = this.data.accessToken)
                        tokenManager.saveRefreshJwt(token = this.data.refreshToken)
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
