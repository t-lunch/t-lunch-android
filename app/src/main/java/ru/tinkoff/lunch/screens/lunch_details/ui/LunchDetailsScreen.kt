package ru.tinkoff.lunch.screens.lunch_details.ui

import androidx.compose.foundation.lazy.items
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.StateFlow
import ru.tinkoff.kotea.compose.collectState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.MutableStateFlow
import ru.tinkoff.lunch.R
import ru.tinkoff.lunch.network.api.auth.model.signup.SignUpResponse
import ru.tinkoff.lunch.network.api.events.model.LunchEvent
import ru.tinkoff.lunch.screens.lunch_details.presentation.LunchDetailsState
import ru.tinkoff.lunch.screens.lunch_details.presentation.LunchDetailsUiEvent

@Composable
fun LunchEventScreen(
    state: StateFlow<LunchDetailsState>,
    onEvent: (LunchDetailsUiEvent) -> Unit = { },
) {
    val uiState by state.collectState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        Text(
            text = stringResource(R.string.lunch_from, uiState.lunch.name, uiState.lunch.surname),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            InfoItem(icon = R.drawable.ic_clock, text = uiState.lunch.place)
            InfoItem(icon = R.drawable.ic_profile, text = uiState.lunch.timeCustom!!)
            InfoItem(
                icon = R.drawable.ic_participants,
                text = pluralStringResource(
                    R.plurals.number_of_participants,
                    uiState.lunch.numberOfParticipants,
                    uiState.lunch.numberOfParticipants,
                )
            )
        }

        Spacer(Modifier.height(24.dp))

        Text(text = "Примечания", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(8.dp))
        Text(
            text = uiState.lunch.description,
            color = colorResource(R.color.notes_color),
            fontSize = 14.sp,
            lineHeight = 20.sp,
        )

        Spacer(Modifier.height(24.dp))

        Text("Участники", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(8.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(uiState.lunch.users) { participant ->
                ParticipantItem(
                    nameAndSurname = "${participant.name} ${participant.surname}",
                    onTelegramClick = {
                        onEvent(LunchDetailsUiEvent.OnTelegramClicked(participant.tg))
                    },
                )
            }
        }

        Spacer(Modifier.weight(1f))

        Button(
            onClick = { onEvent(LunchDetailsUiEvent.JoinLunch) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.yellow)),
            shape = RoundedCornerShape(12.dp),
        ) {
            Text(
                text = "Присоединиться",
                color = Color.Black,
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
            )
        }
    }
}

@Composable
private fun InfoItem(
    @DrawableRes icon: Int,
    text: String,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(
            modifier = Modifier
                .background(color = colorResource(R.color.blue), shape = CircleShape)
                .size(40.dp),
            enabled = false,
            onClick = { },
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(icon),
                contentDescription = null,
                tint = colorResource(R.color.white),
            )
        }
        Spacer(Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 14.sp,
        )
    }
}

@Composable
private fun ParticipantItem(
    nameAndSurname: String,
    onTelegramClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = nameAndSurname,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )
        IconButton(
            modifier = Modifier.size(24.dp),
            onClick = onTelegramClick,
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_telegram),
                contentDescription = null,
                tint = colorResource(R.color.blue),
            )
        }
    }
}

@Preview(
    showBackground = true,
    locale = "ru",
)
@Composable
private fun LunchEventScreenPreview() {
    MaterialTheme {
        LunchEventScreen(
            state = MutableStateFlow(
                LunchDetailsState(
                    lunch = LunchEvent(
                        id = "123",
                        place = "Кухня",
                        numberOfParticipants = 2,
                        timeCustom = "13:00",
                        description = "Какое-то примечание (не редактируемое) Какое-то примечание (не редактируемое) " +
                                "Какое-то примечание (не редактируемое) Какое-то примечание (не редактируемое) " +
                                "Какое-то примечание (не редактируемое)",
                        users = listOf(
                            SignUpResponse(
                                userId = "1",
                                name = "Иван",
                                surname = "Иванов",
                                tg = "ivan.ivanov",
                                office = "Кухня",
                                emoji = "🍽",
                            ),
                            SignUpResponse(
                                userId = "2",
                                name = "Иван",
                                surname = "Иванов",
                                tg = "ivan.ivanov",
                                office = "Кухня",
                                emoji = "🍽",
                            ),
                        ),
                    )
                )
            ),
            onEvent = {},
        )
    }
}
