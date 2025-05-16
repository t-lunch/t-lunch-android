package ru.tinkoff.lunch.screens.main.ui.recycler.model

import android.content.Context
import android.content.res.Configuration
import android.view.View
import ru.tinkoff.lunch.R
import ru.tinkoff.lunch.databinding.ItemLunchEventBinding
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Calendar.HOUR
import java.util.Calendar.MINUTE
import java.util.Date
import java.util.Locale

data class ItemLunchEvent(
    val creator: String = "nightsift48",
    val place: String = "Кухня",
    val time: Date = sampleTime,
    val numberOfParticipants: Int = 2,
) : ViewTyped {

    companion object {
        private val sampleTime = Calendar.getInstance()
            .apply {
                set(HOUR, 13)
                set(MINUTE, 0)
            }.time
    }

    override val uid: String
        get() = creator + place + time + numberOfParticipants
    override val viewType: Int
        get() = R.layout.item_lunch_event
}

class LunchEventItemViewHolder(
    view: View,
    private val onCardClick: (String) -> Unit,
) : BaseViewHolder<ItemLunchEvent>(view) {

    companion object {
        fun getLocalizedContext(context: Context, locale: Locale): Context {
            val configurationWithRussianLocale = Configuration(context.resources.configuration)
                .apply { setLocale(locale) }
            return context.createConfigurationContext(configurationWithRussianLocale)
        }
    }

    private val binding = ItemLunchEventBinding.bind(view)

    override fun bind(item: ItemLunchEvent) {
        val context = binding.root.context

        binding.cardTitle.text = context.getString(R.string.lunch_from, item.creator)
        binding.place.text = item.place

        val numberOfParticipants = getLocalizedContext(
            context = context,
            locale = Locale("ru"),
        )
            .resources
            .getQuantityString(
                R.plurals.number_of_participants,
                item.numberOfParticipants,
                item.numberOfParticipants
            )
        binding.numberOfParticipants.text = numberOfParticipants

        binding.time.text = context.getString(R.string.time_of_lunch, formatTimeFromDate(item.time))

        binding.card.setOnClickListener { onCardClick(item.uid) }
    }

    private fun formatTimeFromDate(date: Date): String {
        val formatter = SimpleDateFormat("HH:mm", Locale("ru"))
        return formatter.format(date)
    }
}
