package ru.tinkoff.lunch.screens.main.ui.recycler

import android.view.View
import ru.tinkoff.lunch.R
import ru.tinkoff.lunch.common.recycler.items.ItemHeaderViewHolder
import ru.tinkoff.lunch.screens.main.ui.recycler.model.LunchEventItemViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.base.CoroutinesHolderFactory

class MainFragmentHolderFactory(
    private val onCardClick: (String) -> Unit,
    private val onJoinClick: () -> Unit,
) : CoroutinesHolderFactory() {

    override fun createViewHolder(view: View, viewType: Int): BaseViewHolder<*>? {
        return when (viewType) {
            R.layout.item_header -> ItemHeaderViewHolder(view)
            R.layout.item_lunch_event -> LunchEventItemViewHolder(view, onCardClick, onJoinClick)
            else -> null
        }
    }
}
