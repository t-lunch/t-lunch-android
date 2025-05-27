package ru.tinkoff.lunch

import org.junit.Test
import ru.tinkoff.lunch.network.api.auth.model.signup.SignUpBody
import ru.tinkoff.lunch.root.sign_up.presentation.SignUpCommand
import ru.tinkoff.lunch.root.sign_up.presentation.SignUpCommandResultEvent
import ru.tinkoff.lunch.root.sign_up.presentation.SignUpNews
import ru.tinkoff.lunch.root.sign_up.presentation.SignUpState
import ru.tinkoff.lunch.root.sign_up.presentation.SignUpUiEvent
import ru.tinkoff.lunch.root.sign_up.presentation.SignUpUpdate
import kotlin.test.assertEquals

class SignUpUpdateTest {

    private val update = SignUpUpdate()
    private val initialState = SignUpState()

    @Test
    fun `when SignInClicked, then produce OpenSignIn news`() {
        val event = SignUpUiEvent.SignInClicked
        val next = update.update(initialState, event)
        assertEquals(initialState, next.state)
        assertEquals(emptyList(), next.commands)
        assertEquals(listOf(SignUpNews.OpenSignIn), next.news)
    }

    @Test
    fun `when EmojiClicked, then update state with new emoji`() {
        val event = SignUpUiEvent.EmojiClicked(emoji = "😎")
        val next = update.update(initialState, event)
        assertEquals(initialState.copy(selectedEmoji = "😎"), next.state)
        assertEquals(emptyList(), next.commands)
        assertEquals(emptyList(), next.news)
    }

    @Test
    fun `when OfficeClicked, then update state with new office`() {
        val event = SignUpUiEvent.OfficeClicked(office = "Office B")
        val next = update.update(initialState, event)
        assertEquals(initialState.copy(selectedOffice = "Office B"), next.state)
        assertEquals(emptyList(), next.commands)
        assertEquals(emptyList(), next.news)
    }

    @Test
    fun `when SignUpClicked, then produce ShowLoading news and SignUp command`() {
        val event = SignUpUiEvent.SignUpClicked(
            login = "test@example.com",
            password = "password",
            name = "John",
            surname = "Doe",
            telegram = "@johndoe"
        )
        val next = update.update(initialState, event)
        val expectedCommand = SignUpCommand.SignUp(
            signUpBody = SignUpBody(
                email = "test@example.com",
                password = "password",
                name = "John",
                surname = "Doe",
                tg = "@johndoe",
                emoji = initialState.selectedEmoji,
                office = initialState.selectedOffice
            )
        )
        assertEquals(initialState, next.state)
        assertEquals(listOf(expectedCommand), next.commands)
        assertEquals(listOf(SignUpNews.ShowLoading), next.news)
    }

    @Test
    fun `when SignUpSuccess, then produce OpenMainScreen news`() {
        val event = SignUpCommandResultEvent.SignUpSuccess
        val next = update.update(initialState, event)
        assertEquals(initialState, next.state)
        assertEquals(emptyList(), next.commands)
        assertEquals(listOf(SignUpNews.OpenMainScreen), next.news)
    }

    @Test
    fun `when SignUpError, then produce ShowError news with message`() {
        val event = SignUpCommandResultEvent.SignUpError(message = "Error message")
        val next = update.update(initialState, event)
        assertEquals(initialState, next.state)
        assertEquals(emptyList(), next.commands)
        assertEquals(listOf(SignUpNews.ShowError(message = "Error message")), next.news)
    }
}
