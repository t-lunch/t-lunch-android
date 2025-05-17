package ru.tinkoff.lunch.screens.main.ui.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.tinkoff.lunch.R

@Composable
fun JoinButton(
    onClick: () -> Unit,
    isLoading: Boolean = false,
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
        ,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.yellow)),
        shape = RoundedCornerShape(12.dp),
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = colorResource(R.color.black),
                strokeWidth = 2.dp,
            )
        } else {
            Text(
                text = "Присоединиться",
                fontSize = 14.sp,
                color = colorResource(R.color.black),
                fontWeight = FontWeight.Normal,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JoinButtonPreviewNoLoader() {
    JoinButton(onClick = {}, isLoading = false)
}

@Preview(showBackground = true)
@Composable
fun JoinButtonPreviewWithLoader() {
    JoinButton(onClick = {}, isLoading = true)
}
