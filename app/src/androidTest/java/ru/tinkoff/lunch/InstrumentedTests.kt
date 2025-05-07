package ru.tinkoff.lunch

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import ru.tinkoff.lunch.screens.main.ui.recycler.model.LunchEventItemViewHolder
import java.util.Locale

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class InstrumentedTests {

    @Test
    fun testPlurals() {
        val context = LunchEventItemViewHolder.getLocalizedContext(
            context = InstrumentationRegistry.getInstrumentation().targetContext,
            locale = Locale("ru"),
        )

        // Проверяем разные формы
        assertEquals("1 участник", context.resources.getQuantityString(
            R.plurals.number_of_participants, 1, 1))
        assertEquals("2 участника", context.resources.getQuantityString(
            R.plurals.number_of_participants, 2, 2))
        assertEquals("5 участников", context.resources.getQuantityString(
            R.plurals.number_of_participants, 5, 5))
        assertEquals("11 участников", context.resources.getQuantityString(
            R.plurals.number_of_participants, 11, 11))
        assertEquals("21 участник", context.resources.getQuantityString(
            R.plurals.number_of_participants, 21, 21))
    }
}
