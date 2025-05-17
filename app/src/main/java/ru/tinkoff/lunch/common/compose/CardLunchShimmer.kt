package ru.tinkoff.lunch.common.compose

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun CardLunchShimmer(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .size(height = 248.dp, width = 380.dp),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Gray)
    ) {}
}

@Preview(showBackground = true)
@Composable
private fun CardLunchSingleShimmerPreview() {
    CardLunchShimmer(modifier = Modifier.shimmer())
}
