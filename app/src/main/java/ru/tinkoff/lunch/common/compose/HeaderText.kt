package ru.tinkoff.lunch.common.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ru.tinkoff.lunch.R

@Composable
fun HeaderText(text: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = text,
        color = colorResource(R.color.black),
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
    )
}

@Preview(showBackground = true)
@Composable
private fun HeaderTextPreview() {
    HeaderText(text = "Обеды")
}
