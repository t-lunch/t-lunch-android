package ru.tinkoff.lunch.screens.main.ui.recycler

import android.view.View
import ru.tinkoff.lunch.R
import ru.tinkoff.lunch.common.recycler.items.ItemHeaderViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.base.CoroutinesHolderFactory

class MainFragmentHolderFactory : CoroutinesHolderFactory() {

    override fun createViewHolder(view: View, viewType: Int): BaseViewHolder<*>? {
        return when (viewType) {
            R.layout.item_header -> ItemHeaderViewHolder(view)
            else -> null
        }
    }
}
