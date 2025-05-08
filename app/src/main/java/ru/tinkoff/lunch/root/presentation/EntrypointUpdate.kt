package ru.tinkoff.lunch.root.presentation

import ru.tinkoff.kotea.core.dsl.DslUpdate

internal class EntrypointUpdate :
    DslUpdate<EntrypointState, EntrypointEvent, EntrypointCommand, EntrypointNews>() {

    override fun NextBuilder.update(event: EntrypointEvent) {
        when (event) {
            is EntrypointCommandResultEvent -> handleCommandResultEvent(event)
            is EntrypointUiEvent -> handleUiEvent(event)
        }
    }

    private fun NextBuilder.handleUiEvent(event: EntrypointUiEvent) {
        when (event) {
            is EntrypointUiEvent.Init -> {
                if (event.isDeepLogout) {
                    commands(EntrypointCommand.DeleteTokens)
                    news(EntrypointNews.OpenSignInScreen)
                } else {
                    commands(EntrypointCommand.Authenticate)
                }
            }
        }
    }

    private fun NextBuilder.handleCommandResultEvent(event: EntrypointCommandResultEvent) {
        when (event) {
            is EntrypointCommandResultEvent.AuthenticationPassed -> {
                news(EntrypointNews.OpenMainScreen)
            }

            is EntrypointCommandResultEvent.AuthenticationFailed -> {
                news(EntrypointNews.OpenSignInScreen)
            }
        }
    }
}
