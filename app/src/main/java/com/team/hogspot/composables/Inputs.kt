package com.team.hogspot.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team.hogspot.R
import com.team.hogspot.ui.theme.AppTheme
import com.team.hogspot.ui.theme.blue500
import com.team.hogspot.ui.theme.purple500


enum class InputSize {
    XS,
    SM,
    MD,
    LG,
    XL
}

@Composable
fun Input (
    value: String = "",
    onValueChange: (String) -> Unit = {},
    iconId: Int = -1,
    size: InputSize = InputSize.SM,
    shape: Shape = AppTheme.shape.container,
    modifier: Modifier = Modifier,
    label: String = ""
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
            .padding(horizontal = padding, vertical = 0.dp),
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
            TextField(
                value = value,
                label = { Text(text = label, color = AppTheme.colorScheme.textTertiary)},
                onValueChange = { onValueChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                textStyle = AppTheme.typography.h3.copy(
                    color = AppTheme.colorScheme.textPrimary
                ),
                maxLines = 4,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = AppTheme.colorScheme.textPrimary,
                    unfocusedTextColor = AppTheme.colorScheme.textPrimary,
                    cursorColor = blue500,
                    focusedIndicatorColor = purple500,
                )
            )

        }
    }
}



//@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true, backgroundColor = 0xFFFFFFFF)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PreviewPrimaryButton() {

    var search by remember { mutableStateOf("Search...") }
    var username by remember { mutableStateOf("Username...") }
    var password by remember { mutableStateOf("Password...") }
    var xs by remember { mutableStateOf("extra small input box...") }
    var sm by remember { mutableStateOf("small input box...") }
    var md by remember { mutableStateOf("medium input box...") }
    var lg by remember { mutableStateOf("large input box...") }
    var xl by remember { mutableStateOf("extra large input box...") }

    AppTheme {
        Column(
            modifier = Modifier
                .padding(AppTheme.size.medium),
        ) {
            Input(
                value = search,
                onValueChange = { search = it },
                iconId = R.drawable.search_icon,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Input(
                value = username,
                onValueChange = { username = it},
                shape = AppTheme.shape.containerRoundedTop
            )
            Input(
                value = password,
                onValueChange = { password = it},
                shape = AppTheme.shape.containerRoundedBottom,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Input(
                value = xs,
                onValueChange = { xs = it},
                shape = AppTheme.shape.container,
                iconId = -1,
                size = InputSize.XS

            )
            Spacer(modifier = Modifier.height(16.dp))
            Input(
                value = sm,
                onValueChange = { sm = it},
                shape = AppTheme.shape.container,
                iconId = -1,
                size = InputSize.SM

            )
            Spacer(modifier = Modifier.height(16.dp))
            Input(
                value = md,
                onValueChange = { md = it},
                shape = AppTheme.shape.container,
                iconId = -1,
                size = InputSize.MD

            )
            Spacer(modifier = Modifier.height(16.dp))
            Input(
                value = lg,
                onValueChange = { lg = it},
                shape = AppTheme.shape.container,
                iconId = -1,
                size = InputSize.LG

            )
            Spacer(modifier = Modifier.height(16.dp))
            Input(
                value = xl,
                onValueChange = { xl = it},
                shape = AppTheme.shape.container,
                iconId = -1,
                size = InputSize.XL

            )
        }
    }
}