package ru.tinkoff.lunch.screens.main.ui.dialogs.recycler

import android.view.View
import ru.tinkoff.lunch.R
import ru.tinkoff.lunch.screens.main.ui.dialogs.recycler.model.LunchTimeItemHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.base.CoroutinesHolderFactory

class CreateLunchBottomSheetViewHolder : CoroutinesHolderFactory() {

    override fun createViewHolder(view: View, viewType: Int): BaseViewHolder<*>? {
        return when (viewType) {
            R.layout.item_lunch_time -> LunchTimeItemHolder(view)
            else -> null
        }
    }
}
