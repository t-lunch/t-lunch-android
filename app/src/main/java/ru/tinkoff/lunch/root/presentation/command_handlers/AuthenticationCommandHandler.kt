package ru.tinkoff.lunch.root.presentation.command_handlers

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import ru.tinkoff.lunch.root.presentation.EntrypointCommand
import ru.tinkoff.lunch.root.presentation.EntrypointCommandResultEvent.AuthenticationFailed
import ru.tinkoff.lunch.root.presentation.EntrypointEvent


internal class AuthenticationCommandHandler(
//    private val userInfoManager: UserInfoManager,
//    private val profileRepository: ProfileRepository,
) : EntrypointCommandsFlowHandler {

    override fun handle(commands: Flow<EntrypointCommand>): Flow<EntrypointEvent> {
        return commands.filterIsInstance<EntrypointCommand.Authenticate>()
            .transform {
                delay(1000)
                emit(AuthenticationFailed)

//                val userId = userInfoManager.getUserId()
//                if (userId == null) {
//                    emit(AuthenticationFailed(null, false))
//                    return@transform
//                }

//                profileRepository.getProfile(userId)
//                    .suspendOnSuccess {
//                        emit(AuthenticationPassed)
//                    }.suspendOnFailure {
//                        val message = this.message()
//                        emit(AuthenticationFailed(message, message.contains("Not Found")))
//                    }
            }
    }
}
