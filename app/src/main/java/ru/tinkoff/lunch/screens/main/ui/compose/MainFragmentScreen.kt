package ru.tinkoff.lunch.screens.main.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import ru.tinkoff.lunch.screens.main.ui.presentation.MainFragmentState
import ru.tinkoff.lunch.screens.main.ui.presentation.MainUiEvent
import ru.tinkoff.lunch.screens.main.ui.presentation.MainUiEvent.LunchDetailsClicked
import ru.tinkoff.lunch.utils.views.HandlePagingItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainFragmentScreen(
    uiState: MainFragmentState,
    onEvent: (MainUiEvent) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(false) }
    var isFabVisible by remember { mutableStateOf(false) }
    val lunchesPagingItems = uiState.lunchesFlow.collectAsLazyPagingItems()
    val lunchesListState = rememberLazyListState()

    val pullToRefreshState = rememberPullToRefreshState()
    var isRefreshing by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            if (isFabVisible) {
                Fab(onClick = {
                    showBottomSheet = true
                })
            }
        },
        containerColor = Color.White,
    ) { padding ->
        PullToRefreshBox(
            state = pullToRefreshState,
            isRefreshing = isRefreshing,
            onRefresh = {
                isRefreshing = true
                lunchesPagingItems.refresh()
            },
            indicator = {
                Indicator(
                    modifier = Modifier.align(Alignment.TopCenter),
                    isRefreshing = isRefreshing,
                    state = pullToRefreshState,
                    containerColor = Color.White,
                    color = Color.Black,
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                HandlePagingItems(lunchesPagingItems) {
                    onRefresh {
                        isFabVisible = false
                        Shimmers()
                    }
                    onEmpty {
                        isFabVisible = true
                        isRefreshing = false

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp, vertical = 32.dp),
                        ) {
                            item { HeaderText(text = stringResource(R.string.lunches)) }
                            item {
                                Text(
                                    modifier = Modifier.padding(top = 8.dp),
                                    text = stringResource(R.string.no_lunches),
                                    fontSize = 16.sp,
                                    lineHeight = 18.sp,
                                )
                            }
                        }
                    }
                    onError {
                        isFabVisible = false
                        isRefreshing = false

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center),
                            contentPadding = PaddingValues(vertical = 300.dp),
                        ) {
                            item {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = stringResource(R.string.something_went_wrong),
                                    textAlign = TextAlign.Center,
                                    lineHeight = 20.sp,
                                    fontWeight = FontWeight.SemiBold,
                                )
                            }
                        }
                    }
                    onSuccess {
                        isFabVisible = true
                        isRefreshing = false

                        LazyColumn(
                            state = lunchesListState,
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
                            onPagingItems(key = { it.id }) { lunch ->
                                CardLunch(
                                    lunch = lunch,
                                    onCardClick = { onEvent(LunchDetailsClicked(lunch.id)) },
                                )
                            }
                        }
                    }
                }
            }
        }

        if (showBottomSheet) {
            BottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
            )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomSheet(
    onDismissRequest: () -> Unit,
    sheetState: SheetState,
) {
    val paddingModifier = Modifier.padding(horizontal = 16.dp)
    var isKitchenSelected by remember { mutableStateOf(true) }
    var customPlace by remember { mutableStateOf("") }
    var isCustomPlaceError by remember { mutableStateOf(false) }
    var notes by remember { mutableStateOf("") }

    ModalBottomSheet(
        modifier = Modifier,
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = Color.White,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            HeaderText(text = stringResource(R.string.new_event), modifier = paddingModifier)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = stringResource(R.string.time), fontSize = 14.sp, modifier = paddingModifier)
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(10) {
                    ChipItem(text = "12:00", isSelected = true, onClick = { /* todo */ })
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = stringResource(R.string.place),
                fontSize = 14.sp,
                modifier = paddingModifier
            )
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ChipItem(
                    stringResource(R.string.kitchen),
                    isSelected = isKitchenSelected,
                    onClick = { isKitchenSelected = true },
                )
                ChipItem(
                    stringResource(R.string.custom_place),
                    isSelected = !isKitchenSelected,
                    onClick = {
                        isCustomPlaceError = false
                        isKitchenSelected = false
                    },
                )
            }

            if (!isKitchenSelected) {
                OutlinedTextField(
                    value = customPlace,
                    onValueChange = {
                        isCustomPlaceError = false
                        customPlace = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    placeholder = { Text(stringResource(R.string.enter_custom_place_name)) },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Gray),
                    isError = isCustomPlaceError,
                )
            }

            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = stringResource(R.string.notes),
                fontSize = 14.sp,
                modifier = paddingModifier,
            )
            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = { Text(stringResource(R.string.notes_hint)) },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Gray),
            )

            Spacer(modifier = Modifier.size(64.dp))

            Button(
                onClick = {
                    if (customPlace.isEmpty()) isCustomPlaceError = true
                },
                modifier = paddingModifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.yellow)),
                shape = RoundedCornerShape(12.dp),
            ) {
                Text(
                    text = stringResource(R.string.create),
                    color = Color.Black,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                )
            }

            Spacer(modifier = Modifier.size(32.dp))
        }
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


@Composable
private fun ChipItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val color = colorResource(
        if (isSelected) {
            R.color.yellow
        } else {
            R.color.chip_gray
        }
    )
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = color,
        tonalElevation = 0.dp,
        modifier = Modifier.height(32.dp),
        onClick = onClick,
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text,
                fontSize = 14.sp,
                modifier = Modifier
                    .height(32.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                lineHeight = 5.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChipItemPreview() {
    ChipItem(
        text = "12:00",
        isSelected = true,
        onClick = { }
    )
}
