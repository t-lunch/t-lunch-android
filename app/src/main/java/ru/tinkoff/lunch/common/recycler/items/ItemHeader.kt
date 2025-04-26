package ru.tinkoff.lunch.common.recycler.items

import android.view.View
import ru.tinkoff.lunch.R
import ru.tinkoff.lunch.databinding.ItemHeaderBinding
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped

data class ItemHeader(
    val text: String,
) : ViewTyped {
    override val uid: String
        get() = text
    override val viewType: Int
        get() = R.layout.item_header
}

class ItemHeaderViewHolder(view: View) : BaseViewHolder<ItemHeader>(view) {

    private val binding = ItemHeaderBinding.bind(view)

    override fun bind(item: ItemHeader) {
        binding.root.text = item.text
    }
}
