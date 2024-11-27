package com.team.hogspot.composables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.hogspot.R
import com.team.hogspot.ui.theme.AppTheme


enum class InputSize {
    XS,
    SM,
    MD,
    LG,
    XL
}

@Composable
fun Input (
    type: String,
    placeholder: String,
    iconId: Int = -1,
    size: InputSize = InputSize.SM,
    shape: Shape = AppTheme.shape.container,
    noTopBorder: Boolean = false,
    modifier: Modifier = Modifier
) {
    val height = when (size) {
        InputSize.XS -> 56.dp
        InputSize.SM -> 64.dp
        InputSize.MD -> 96.dp
        InputSize.LG -> 128.dp
        InputSize.XL -> 160.dp
    }
    val padding = when (size) {
        InputSize.XS -> 12.dp
        InputSize.SM -> 16.dp
        InputSize.MD -> 16.dp
        InputSize.LG -> 16.dp
        InputSize.XL -> 16.dp
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .border(2.dp, AppTheme.colorScheme.textTertiary, shape)

            .clip(shape)
            .background(AppTheme.colorScheme.backgroundSecondary)
            .padding(padding),
        contentAlignment = Alignment.TopStart

    ) {

        Row (
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (iconId != -1) {
                Icon(
                    painter = painterResource(id = iconId),
                    contentDescription = "HogSpot Logo",
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp),
                    tint = AppTheme.colorScheme.textTertiary
                )
                Spacer(modifier = Modifier.size(16.dp))
            }
            // TODO: replace H3 with input composable
            H3(
                text = placeholder,
                color = AppTheme.colorScheme.textTertiary
            )

        }
    }
}



//@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true, backgroundColor = 0xFFFFFFFF)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PreviewPrimaryButton() {
    AppTheme {
        Column(
            modifier = Modifier
                .padding(AppTheme.size.medium),
        ) {
            Input(
                type="Search",
                placeholder="Search Hogspot",
                iconId = R.drawable.search_icon,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Input(
                type = "Email",
                placeholder = "Email",
                shape = AppTheme.shape.containerRoundedTop
            )
            Input(
                type = "Password",
                placeholder = "Password",
                shape = AppTheme.shape.containerRoundedBottom,
                noTopBorder = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            Input(
                placeholder = "Title...",
                type = "Title",
                shape = AppTheme.shape.container,
                iconId = -1,
                size = InputSize.XS

            )
            Spacer(modifier = Modifier.height(16.dp))
            Input(
                placeholder = "Title...",
                type = "Title",
                shape = AppTheme.shape.container,
                iconId = -1,
                size = InputSize.SM

            )
            Spacer(modifier = Modifier.height(16.dp))
            Input(
                placeholder = "Title...",
                type = "Title",
                shape = AppTheme.shape.container,
                iconId = -1,
                size = InputSize.MD

            )
            Spacer(modifier = Modifier.height(16.dp))
            Input(
                placeholder = "Title...",
                type = "Title",
                shape = AppTheme.shape.container,
                iconId = -1,
                size = InputSize.LG

            )
            Spacer(modifier = Modifier.height(16.dp))
            Input(
                placeholder = "Title...",
                type = "Title",
                shape = AppTheme.shape.container,
                iconId = -1,
                size = InputSize.XL

            )
        }
    }
}