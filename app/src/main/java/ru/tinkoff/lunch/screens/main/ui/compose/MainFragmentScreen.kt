package ru.tinkoff.lunch.screens.main.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer
import ru.tinkoff.lunch.R
import ru.tinkoff.lunch.common.compose.CardLunchShimmer
import ru.tinkoff.lunch.common.compose.HeaderText
import ru.tinkoff.lunch.common.lce.LceState
import ru.tinkoff.lunch.network.api.events.model.LunchEvent
import ru.tinkoff.lunch.screens.main.ui.presentation.MainFragmentState
import ru.tinkoff.lunch.screens.main.ui.presentation.MainUiEvent

@Composable
fun MainFragmentScreen(
    uiState: MainFragmentState,
    onEvent: (MainUiEvent) -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            if (uiState.lunches is LceState.Content) {
                Fab(onClick = { onEvent(MainUiEvent.CreateLunchClicked) })
            }
        },
        containerColor = Color.White,
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (uiState.lunches) {
                is LceState.Loading -> Shimmers()

                is LceState.Content -> {
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
                        item { HeaderText(text = stringResource(R.string.lunches)) }
                        items(uiState.lunches.data) { lunch ->
                            CardLunch(
                                lunch = lunch,
                                onCardClick = { onEvent(MainUiEvent.LunchDetailsClicked(lunch.id)) },
                            )
                        }
                    }
                }

                is LceState.Error -> Unit
            }

        }
    }
}

@Composable
private fun Fab(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
        containerColor = Color.White,
        shape = RoundedCornerShape(100),
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_plus),
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
private fun Shimmers() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 32.dp)
            .fillMaxWidth()
            .shimmer(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        CardLunchShimmer()
        CardLunchShimmer()
        CardLunchShimmer()
        CardLunchShimmer()
    }
}

@Preview(showBackground = true)
@Composable
private fun MainFragmentScreenPreview() {
    MainFragmentScreen(
        uiState = MainFragmentState(
            lunches = LceState.Content(
                listOf(
                    LunchEvent(),
                    LunchEvent(),
                    LunchEvent()
                )
            ),
        ),
        onEvent = { },
    )
}
