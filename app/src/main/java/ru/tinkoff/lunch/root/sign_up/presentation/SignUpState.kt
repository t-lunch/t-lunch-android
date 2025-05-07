package ru.tinkoff.lunch.root.sign_up.presentation

import ru.tinkoff.lunch.common.recycler.items.ItemEmoji
import ru.tinkoff.lunch.common.recycler.items.ItemOffice

data class SignUpState(
    val selectedEmoji: String = ItemEmoji.possibleEmojiList.first(),
    val selectedOffice: String = ItemOffice.possibleOffices.first(),
)
