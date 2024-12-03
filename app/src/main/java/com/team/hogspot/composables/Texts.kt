package com.team.hogspot.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.team.hogspot.ui.theme.AppTheme

@Composable
fun H1 (
    text: String,
    color: Color = AppTheme.colorScheme.textPrimary,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        style = AppTheme.typography.h1,
        modifier = modifier,
        textAlign = TextAlign.Center
    )
}

@Composable
fun H2 (
    text: String,
    color: Color = AppTheme.colorScheme.textPrimary,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        style = AppTheme.typography.h2,
        modifier = modifier,
        textAlign = TextAlign.Center
    )
}

@Composable
fun H3 (
    text: String,
    color: Color = AppTheme.colorScheme.textPrimary,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        style = AppTheme.typography.h3,
        modifier = modifier,
        textAlign = TextAlign.Center
    )
}

@Composable
fun P (
    text: String,
    color: Color = AppTheme.colorScheme.textPrimary,
    modifier: Modifier = Modifier,
    textAlignment: TextAlign = TextAlign.Start,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        text = text,
        color = color,
        style = AppTheme.typography.p,
        modifier = modifier,
        textAlign = textAlignment,
        fontWeight = fontWeight,
    )
}

@Composable
fun LabelNormal (
    text: String,
    color: Color = AppTheme.colorScheme.textPrimary,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        style = AppTheme.typography.labelNormal,
        modifier = modifier,
        textAlign = TextAlign.Center
    )
}

@Composable
fun LabelSmall (
    text: String,
    color: Color = AppTheme.colorScheme.textPrimary,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        style = AppTheme.typography.labelSmall,
        modifier = modifier,
        textAlign = TextAlign.Center
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PreviewPrimaryButton() {
    AppTheme {
        Column(
            modifier = Modifier
                .padding(AppTheme.size.medium),
            verticalArrangement = Arrangement.spacedBy(AppTheme.size.normal)
        ) {
        H1(
            text = "HogSpot",
        )

        H2 (
            text = "Welcome to HogSpot!",
            color = AppTheme.colorScheme.textSecondary
        )

        H3 (
            text = "UARK geogussr.",
            color = AppTheme.colorScheme.textTertiary
        )

        P(
            text = "This is a paragraph of text.",
        )

        LabelNormal(
            text = "This is a label.",
            color = AppTheme.colorScheme.primary,
        )

        LabelSmall(
            text = "This is a small label.",
        )
        }
    }
}