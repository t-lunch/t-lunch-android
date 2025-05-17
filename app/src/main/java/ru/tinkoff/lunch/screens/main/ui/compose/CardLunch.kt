package ru.tinkoff.lunch.screens.main.ui.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.tinkoff.lunch.R
import ru.tinkoff.lunch.common.compose.DetailIcon
import ru.tinkoff.lunch.network.api.auth.model.signup.SignUpResponse
import ru.tinkoff.lunch.network.api.events.model.LunchEvent

@Composable
fun CardLunch(
    modifier: Modifier = Modifier,
    lunch: LunchEvent,
    onCardClick: () -> Unit = { },
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(32.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        onClick = onCardClick,
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.lunch_from, "Петр", "Петров"),
                fontSize = 17.sp,
                maxLines = 1,
                overflow = TextOverflow.StartEllipsis,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                InfoCircle(icon = R.drawable.ic_map_marker, text = lunch.place)
                InfoCircle(icon = R.drawable.ic_clock, text = lunch.time)
                InfoCircle(
                    icon = R.drawable.ic_participants,
                    text = pluralStringResource(
                        R.plurals.number_of_participants,
                        lunch.numberOfParticipants,
                        lunch.numberOfParticipants
                    ),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            JoinButton(
                onClick = { },
                isLoading = false,
            )
        }
    }
}

@Composable
private fun InfoCircle(
    @DrawableRes icon: Int,
    text: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        DetailIcon(icon = icon)
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 14.sp,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun CardLunchPreview() {
    CardLunch(
        lunch = LunchEvent(
            id = "123",
            creator = "nightshift48",
            place = "Кухня",
            numberOfParticipants = 2,
            time = "13:00",
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
}
