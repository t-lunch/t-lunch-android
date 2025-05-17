package ru.tinkoff.lunch.common.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.tinkoff.lunch.R

@Composable
fun DetailIcon(@DrawableRes icon: Int) {
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
}

@Preview(showBackground = true)
@Composable
fun DetailIconPreview() {
    DetailIcon(R.drawable.ic_clock)
}
