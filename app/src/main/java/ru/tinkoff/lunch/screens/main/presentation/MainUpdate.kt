package ru.tinkoff.lunch.screens.main.presentation

import ru.tinkoff.kotea.core.dsl.DslUpdate

internal class MainUpdate :
    DslUpdate<MainState, MainEvent, MainCommand, MainNews>() {

    override fun NextBuilder.update(event: MainEvent) {
        when (event) {
            is MainCommandResultEvent -> handleCommandResultEvent(event)
            else -> Unit
        }
    }

    private fun NextBuilder.handleCommandResultEvent(event: MainCommandResultEvent) {
        when (event) {
            else -> Unit
        }
    }
}
