package ru.tinkoff.lunch.screens.main.ui.dialogs.recycler.model

import android.view.View
import ru.tinkoff.lunch.R
import ru.tinkoff.lunch.databinding.ItemLunchTimeBinding
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped

data class ItemLunchTime(
    val time: String,
    val isSelected: Boolean = false,
) : ViewTyped {

    override val uid: String
        get() = time
    override val viewType: Int
        get() = R.layout.item_lunch_time
}

class LunchTimeItemHolder(view: View) : BaseViewHolder<ItemLunchTime>(view) {

    private val binding = ItemLunchTimeBinding.bind(view)

    override fun bind(item: ItemLunchTime) {
        binding.root.text = item.time

        val context = binding.root.context
        binding.root.setBackgroundColor(
            if (item.isSelected) {
                context.getColor(R.color.yellow)
            } else {
                context.getColor(R.color.chip_gray)
            }
        )
    }
}
