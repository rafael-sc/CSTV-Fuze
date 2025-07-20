package com.orafaelsc.cstvfuze.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TagComponent(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
    text: String,
    textColor: Color
) {
    Box(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 16.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 16.dp
                )
            )
            .background(backgroundColor)
            .padding(horizontal = 12.dp, vertical = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNowTagComponent() {
    TagComponent(
        backgroundColor = Color.Red,
        text = "AGORA",
        textColor = Color.White
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDateAndTimeComponent() {
    TagComponent(
        backgroundColor = Color.Gray,
        text = "HOJE, 19:00",
        textColor = Color.White
    )
}
