package ru.tinkoff.lunch.root.sign_up.ui.mapper

import ru.tinkoff.lunch.common.recycler.items.ItemEmoji
import ru.tinkoff.lunch.common.recycler.items.ItemOffice

data class SignUpUiState(
    val emojiList: List<ItemEmoji> = emptyList(),
    val officesList: List<ItemOffice> = emptyList(),
)
