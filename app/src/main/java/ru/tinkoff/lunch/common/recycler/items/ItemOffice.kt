package ru.tinkoff.lunch.common.recycler.items

import android.view.View
import ru.tinkoff.lunch.R
import ru.tinkoff.lunch.databinding.ItemOfficeBinding
import ru.tinkoff.lunch.utils.views.dpToPx
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped

data class ItemOffice(
    val name: String,
    val isSelected: Boolean = false,
) : ViewTyped {

    companion object {
        val possibleOffices: List<String> = listOf(
            "Нижний Новгород",
            "Москва",
            "Санкт-Петербург",
            "Казань",
            "Новосибирск",
            "Екатеринбург",
            "Красноярск",
            "Воронеж",
            "Ростов-на-Дону",
            "Самара",
        )
    }

    override val uid: String
        get() = name + isSelected
    override val viewType: Int
        get() = R.layout.item_office
}

class OfficeItemViewHolder(
    view: View,
    private val onClick: (String) -> Unit,
) : BaseViewHolder<ItemOffice>(view) {

    private val binding = ItemOfficeBinding.bind(view)

    override fun bind(item: ItemOffice) {
        val context = binding.root.context

        binding.office.text = item.name

        binding.root.strokeColor = if (item.isSelected) {
            context.getColor(R.color.blue)
        } else {
            context.getColor(R.color.shimmerColor)
        }
        binding.root.strokeWidth = if (item.isSelected) 2.dpToPx(context) else 1.dpToPx(context)

        binding.root.setOnClickListener { onClick(item.name) }
    }
}
