import org.junit.Test
import ru.tinkoff.lunch.screens.lunch_details.presentation.*
import ru.tinkoff.kotea.core.Next
import ru.tinkoff.lunch.network.api.events.model.LunchEvent
import kotlin.test.assertEquals

class LunchDetailsUpdateTest {

    private val update = LunchDetailsUpdate()

    @Test
    fun `when OnTelegramClicked, then produce OpenTelegram news`() {
        val initialState = LunchDetailsState(lunch = LunchEvent(id = "123"))
        val event = LunchDetailsUiEvent.OnTelegramClicked(telegramId = "nightshift48")
        val next: Next<LunchDetailsState, LunchDetailsCommand, LunchDetailsNews> = update.update(initialState, event)

        assertEquals(listOf(LunchDetailsNews.OpenTelegram(telegramId = "nightshift48")), next.news)
        assertEquals(initialState, next.state)
        assertEquals(emptyList(), next.commands)
    }

    @Test
    fun `when GetLunchDetailsSuccess, then update state with new lunch`() {
        val initialState = LunchDetailsState(lunch = LunchEvent(id = "123"))
        val newLunch = LunchEvent(id = "456")
        val event = LunchDetailsCommandResultEvent.GetLunchDetailsSuccess(lunch = newLunch)
        val next: Next<LunchDetailsState, LunchDetailsCommand, LunchDetailsNews> = update.update(initialState, event)

        assertEquals(LunchDetailsState(lunch = newLunch), next.state)
        assertEquals(emptyList(), next.news)
        assertEquals(emptyList(), next.commands)
    }

    @Test
    fun `when JoinLunch, then no changes`() {
        val initialState = LunchDetailsState(lunch = LunchEvent(id = "123"))
        val event = LunchDetailsUiEvent.JoinLunch
        val next: Next<LunchDetailsState, LunchDetailsCommand, LunchDetailsNews> = update.update(initialState, event)

        assertEquals(initialState, next.state)
        assertEquals(listOf(LunchDetailsNews.JoinLunch), next.news)
        assertEquals(emptyList(), next.commands)
    }
}
