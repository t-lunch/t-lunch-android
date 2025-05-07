package ru.tinkoff.lunch.root.sign_up.ui.recycler

import android.view.View
import ru.tinkoff.lunch.R
import ru.tinkoff.lunch.common.recycler.items.EmojiItemViewHolder
import ru.tinkoff.lunch.common.recycler.items.OfficeItemViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.base.CoroutinesHolderFactory

class SignUpViewHolderFactory(
    private val onEmojiClickListener: (String) -> Unit,
    private val onOfficeClickListener: (String) -> Unit,
) : CoroutinesHolderFactory() {

    override fun createViewHolder(view: View, viewType: Int): BaseViewHolder<*>? {
        return when (viewType) {
            R.layout.item_emoji -> EmojiItemViewHolder(view, onEmojiClickListener)
            R.layout.item_office -> OfficeItemViewHolder(view, onOfficeClickListener)
            else -> null
        }
    }
}
