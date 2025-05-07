package ru.tinkoff.lunch.root.sign_up.ui.mapper

import ru.tinkoff.kotea.android.ui.ResourcesProvider
import ru.tinkoff.kotea.android.ui.UiStateMapper
import ru.tinkoff.lunch.common.recycler.items.ItemEmoji
import ru.tinkoff.lunch.common.recycler.items.ItemOffice
import ru.tinkoff.lunch.root.sign_up.presentation.SignUpState

class SignUpUiStateMapper : UiStateMapper<SignUpState, SignUpUiState> {

    override fun ResourcesProvider.map(state: SignUpState): SignUpUiState {
        val emojiList = buildList {
            ItemEmoji.possibleEmojiList.forEach { emoji ->
                add(
                    ItemEmoji(
                        emoji = emoji,
                        isSelected = emoji == state.selectedEmoji,
                    )
                )
            }
        }

        val officeList = buildList {
            ItemOffice.possibleOffices.forEach { office ->
                add(
                    ItemOffice(
                        name = office,
                        isSelected = office == state.selectedOffice,
                    )
                )
            }
        }

        return SignUpUiState(
            emojiList = emojiList,
            officesList = officeList,
        )
    }
}
