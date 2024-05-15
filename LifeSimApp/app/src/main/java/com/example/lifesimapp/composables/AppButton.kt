// Auth: John O'Neal
// Date: 05/09/2024
// Desc: A button composable that takes a text and a click handler.

package com.example.lifesimapp.composables

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.width(200.dp)
    ) {
        Text(text = text)
    }
}

@Preview
@Composable
fun AppButtonPreview() {
    AppButton(text = "Click me", onClick = {})
}