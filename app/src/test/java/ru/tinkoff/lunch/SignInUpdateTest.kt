package ru.tinkoff.lunch

import ru.tinkoff.lunch.root.sign_in.presentation.SignInCommand
import ru.tinkoff.lunch.root.sign_in.presentation.SignInCommandResultEvent
import ru.tinkoff.lunch.root.sign_in.presentation.SignInNews
import ru.tinkoff.lunch.root.sign_in.presentation.SignInState
import ru.tinkoff.lunch.root.sign_in.presentation.SignInUiEvent
import ru.tinkoff.lunch.root.sign_in.presentation.SignInUpdate
import kotlin.test.Test
import kotlin.test.assertEquals

class SignInUpdateTest {

    private val update = SignInUpdate()
    private val initialState = SignInState()

    @Test
    fun testSignUpClicked() {
        val event = SignInUiEvent.SignUpClicked
        val next = update.update(initialState, event)
        assertEquals(initialState, next.state)
        assertEquals(emptyList<SignInCommand>(), next.commands)
        assertEquals(listOf(SignInNews.OpenSignUp), next.news)
    }

    @Test
    fun testLoginClicked() {
        val login = "user@example.com"
        val password = "password123"
        val event = SignInUiEvent.LoginClicked(login, password)
        val next = update.update(initialState, event)
        assertEquals(initialState, next.state)
        assertEquals(listOf(SignInCommand.SignIn(login, password)), next.commands)
        assertEquals(listOf(SignInNews.ShowLoading), next.news)
    }

    @Test
    fun testSignInSuccess() {
        val event = SignInCommandResultEvent.SignInSuccess
        val next = update.update(initialState, event)
        assertEquals(initialState, next.state)
        assertEquals(emptyList<SignInCommand>(), next.commands)
        assertEquals(listOf(SignInNews.OpenMainScreen), next.news)
    }

    @Test
    fun testSignInError() {
        val message = "Invalid credentials"
        val event = SignInCommandResultEvent.SignInError(message)
        val next = update.update(initialState, event)
        assertEquals(initialState, next.state)
        assertEquals(emptyList<SignInCommand>(), next.commands)
        assertEquals(listOf(SignInNews.ShowError(message)), next.news)
    }
}
