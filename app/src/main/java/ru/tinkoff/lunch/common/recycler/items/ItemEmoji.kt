package ru.tinkoff.lunch.common.recycler.items

import android.view.View
import ru.tinkoff.lunch.R
import ru.tinkoff.lunch.databinding.ItemEmojiBinding
import ru.tinkoff.lunch.utils.views.dpToPx
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped

data class ItemEmoji(
    val emoji: String = "\uD83D\uDE36\u200D\uD83C\uDF2B\uFE0F",
    val isSelected: Boolean = false,
) : ViewTyped {

    companion object {
        val possibleEmojiList: List<String> = listOf(
            "\uD83D\uDE04",
            "\uD83E\uDD20",
            "\uD83E\uDD77\uD83C\uDFFF",
            "\uD83D\uDC3B\u200D❄\uFE0F",
            "\uD83E\uDDA9",
            "\uD83E\uDD78",
            "\uD83D\uDE2E\u200D\uD83D\uDCA8",
            "\uD83E\uDDA5",
            "\uD83E\uDD2B",
            "\uD83E\uDD2D",
            "\uD83D\uDE4A",
        )
    }

    override val uid: String
        get() = emoji
    override val viewType: Int
        get() = R.layout.item_emoji
}

class EmojiItemViewHolder(
    view: View,
    private val onClick: (String) -> Unit,
) : BaseViewHolder<ItemEmoji>(view) {

    private val binding = ItemEmojiBinding.bind(view)

    override fun bind(item: ItemEmoji) {
        val context = binding.root.context

        binding.emoji.text = item.emoji
        binding.root.strokeColor = if (item.isSelected) {
            context.getColor(R.color.blue)
        } else {
            context.getColor(R.color.shimmerColor)
        }
        binding.root.strokeWidth = if (item.isSelected) 2.dpToPx(context) else 1.dpToPx(context)

        binding.root.setOnClickListener { onClick(item.emoji) }
    }
}
