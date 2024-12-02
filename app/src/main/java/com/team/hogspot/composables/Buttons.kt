package com.team.hogspot.composables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team.hogspot.R
import com.team.hogspot.ui.theme.AppTheme


@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    text: String,
    iconId: Int? = null,
    shape: Shape = AppTheme.shape.button,
    isActive: Boolean = true,
    modifier: Modifier = Modifier
) {
    val buttonColor = if (isActive) {
        AppTheme.colorScheme.primary
    } else {
        AppTheme.colorScheme.textTertiary
    }

    val textColor = if (isActive) {
        AppTheme.colorScheme.background
    } else {
        AppTheme.colorScheme.backgroundSecondary
    }
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor // Button background color
        ),
        shape = shape
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)

        ) {
            if (iconId != null) {
                Icon(
                    painter = painterResource(id = iconId),
                    contentDescription = "HogSpot Logo",
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp),
                    tint = AppTheme.colorScheme.background
                )
            }

            Text(
                text = text,
                color = textColor,
                style = AppTheme.typography.labelNormal
            )
        }
    }

}

@Composable
fun SecondaryButton(
    onClick: () -> Unit,
    text: String,
    iconId: Int? = null,
    shape: Shape = AppTheme.shape.button,
    textColor: Color = AppTheme.colorScheme.primary,
    iconColor: Color? = null,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    modifier: Modifier = Modifier
) {
    val iconTint = if (iconColor == null ) {
        Color.Unspecified
    } else {
        iconColor
    }

    val spacerDp = if (horizontalArrangement == Arrangement.Center) {
        12.dp
    } else {
        78.dp
    }

    OutlinedButton(
        onClick = onClick,
        shape = shape,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent // Button background color
        ),
        border = BorderStroke(2.dp, AppTheme.colorScheme.textTertiary)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = horizontalArrangement,
            modifier = Modifier.fillMaxWidth()

        ) {
            if (iconId != null) {
                Icon(
                    painter = painterResource(id = iconId),
                    contentDescription = "HogSpot Logo",
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp),
                    tint = iconColor ?: Color.Unspecified
                )
            }
            
            Spacer(modifier = Modifier.width(spacerDp))

            Text(
                text = text,
                color = textColor,
                style = AppTheme.typography.labelNormal
            )
        }
    }

}

//@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true, backgroundColor = 0xFFFFFFFF)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PreviewButtons() {
    AppTheme {
        Column(
            modifier = Modifier
                .padding(AppTheme.size.medium),
            verticalArrangement = Arrangement.spacedBy(AppTheme.size.normal)
        ) {
            PrimaryButton(
                text = "Primary",
                onClick = {}

            )
            SecondaryButton(
                text = "Secondary",
                onClick = {}
            )

            PrimaryButton(
                text = "Primary Icon",
                onClick = {},
                iconId = R.drawable.plus_icon,
                modifier = Modifier
                    .height(64.dp),
                shape = AppTheme.shape.container
            )

            SecondaryButton(
                text = "Secondary Icon",
                onClick = {},
                iconId = R.drawable.plus_icon,
                modifier = Modifier
                    .height(64.dp),
                shape = AppTheme.shape.container,
                iconColor = AppTheme.colorScheme.primary
            )

            SecondaryButton(
                text = "Image",
                onClick = {},
                iconId = R.drawable.down_arrow_icon,
                modifier = Modifier
                    .height(128.dp),
                shape = AppTheme.shape.container,
                iconColor = AppTheme.colorScheme.primary
            )
        }
    }
}