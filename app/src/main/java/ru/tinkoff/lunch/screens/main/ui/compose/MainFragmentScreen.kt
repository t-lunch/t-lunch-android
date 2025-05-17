package ru.tinkoff.lunch.screens.main.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.tinkoff.lunch.R
import ru.tinkoff.lunch.common.compose.HeaderText
import ru.tinkoff.lunch.network.api.events.model.LunchEvent
import ru.tinkoff.lunch.screens.main.ui.presentation.MainFragmentState
import ru.tinkoff.lunch.screens.main.ui.presentation.MainUiEvent

@Composable
fun MainFragmentScreen(
    uiState: MainFragmentState,
    onEvent: (MainUiEvent) -> Unit,
) {
    Scaffold(
        floatingActionButton = { /* todo */ },
        containerColor = Color.White,
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(
                    top = 32.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 64.dp
                ),
            ) {
                // todo: add shimmers
                item { HeaderText(text = stringResource(R.string.lunches)) }
                items(uiState.lunches) { lunch ->
                    CardLunch(
                        lunch = lunch,
                        onCardClick = { onEvent(MainUiEvent.LunchDetailsClicked(lunch.id)) },
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainFragmentScreenPreview() {
    MainFragmentScreen(
        uiState = MainFragmentState(
            isLoading = false,
            lunches = listOf(LunchEvent(), LunchEvent(), LunchEvent()),
        ),
        onEvent = { },
    )
}
