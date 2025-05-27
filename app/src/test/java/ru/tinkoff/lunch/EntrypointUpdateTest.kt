package ru.tinkoff.lunch

import ru.tinkoff.lunch.root.presentation.EntrypointCommand
import ru.tinkoff.lunch.root.presentation.EntrypointCommandResultEvent
import ru.tinkoff.lunch.root.presentation.EntrypointNews
import ru.tinkoff.lunch.root.presentation.EntrypointState
import ru.tinkoff.lunch.root.presentation.EntrypointUiEvent
import ru.tinkoff.lunch.root.presentation.EntrypointUpdate
import kotlin.test.Test
import kotlin.test.assertEquals

class EntrypointUpdateTest {

    private val update = EntrypointUpdate()
    private val initialState = EntrypointState()

    @Test
    fun `when Init with deep logout, then delete tokens and open sign in screen`() {
        val event = EntrypointUiEvent.Init(isDeepLogout = true)
        val next = update.update(initialState, event)
        assertEquals(initialState, next.state)
        assertEquals(listOf(EntrypointCommand.DeleteTokens), next.commands)
        assertEquals(listOf(EntrypointNews.OpenSignInScreen), next.news)
    }

    @Test
    fun `when Init without deep logout, then authenticate`() {
        val event = EntrypointUiEvent.Init(isDeepLogout = false)
        val next = update.update(initialState, event)
        assertEquals(initialState, next.state)
        assertEquals(listOf(EntrypointCommand.Authenticate), next.commands)
        assertEquals(emptyList(), next.news)
    }

    @Test
    fun `when AuthenticationPassed, then open main screen`() {
        val event = EntrypointCommandResultEvent.AuthenticationPassed
        val next = update.update(initialState, event)
        assertEquals(initialState, next.state)
        assertEquals(emptyList(), next.commands)
        assertEquals(listOf(EntrypointNews.OpenMainScreen), next.news)
    }

    @Test
    fun `when AuthenticationFailed, then open sign in screen`() {
        val event = EntrypointCommandResultEvent.AuthenticationFailed
        val next = update.update(initialState, event)
        assertEquals(initialState, next.state)
        assertEquals(emptyList(), next.commands)
        assertEquals(listOf(EntrypointNews.OpenSignInScreen), next.news)
    }
}
