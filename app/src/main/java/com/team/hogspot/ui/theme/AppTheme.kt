package com.team.hogspot.ui.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// AppTheme.kt
// defines the values for the interface defined in AppDesignSystem.kt
// provides those values to the app via AppTheme object

private val darkColorScheme = AppColorScheme(
    primary = rose500,
    background = stone800,
    backgroundSecondary = stone700,
    onBackground = stone650,
    textPrimary = white,
    textSecondary = stone500,
    textTertiary = stone600,
    profileColors = listOf(
        red500,
        orange500,
        green500,
        blue500,
        purple500,
        gray500,
    ),
    difficultyEasy = green500,
    difficultyMedium = orange500,
    difficultyHard = red500,
)

private val lightColorScheme = AppColorScheme(
    primary = rose500,
    background = stone800,
    backgroundSecondary = stone700,
    onBackground = stone650,
    textPrimary = white,
    textSecondary = stone500,
    textTertiary = stone600,
    profileColors = listOf(
        red500,
        orange500,
        green500,
        blue500,
        purple500,
        gray500,
    ),
    difficultyEasy = green500,
    difficultyMedium = orange500,
    difficultyHard = red500,
)

private val typography = AppTypography(
    h1 = TextStyle(
        fontFamily = MonomaniacOne,
        fontWeight = FontWeight.Bold,
        fontSize = 42.sp,
    ),
    h2 = TextStyle(
        fontFamily = MonomaniacOne,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
    ),
    h3 = TextStyle(
        fontFamily = MonomaniacOne,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
    ),
    p = TextStyle(
        fontFamily = MonomaniacOne,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),
    labelNormal = TextStyle(
        fontFamily = MonomaniacOne,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = MonomaniacOne,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
)

private val shape = AppShape(
    container = RoundedCornerShape(16.dp),
    containerSmall = RoundedCornerShape(8.dp),
    button = RoundedCornerShape(50),
    profileImage = RoundedCornerShape(100),
    containerRoundedTop = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 16.dp,
        bottomStart = 0.dp,
        bottomEnd = 0.dp
    ),
    containerRoundedBottom = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomStart = 16.dp,
        bottomEnd = 16.dp
    ),
    containerRoundedNone = RoundedCornerShape(0.dp),
    tag = RoundedCornerShape(12.dp),

)

private val size = AppSize(
    large = 24.dp,
    medium = 16.dp,
    normal = 12.dp,
    small = 8.dp,
)

@Composable
fun AppTheme (
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit

) {
    val colorScheme = if (!isDarkTheme) darkColorScheme else lightColorScheme // remove '!' to switch back to light theme
    val rippleIndication = ripple() // interactions with UI elements
    CompositionLocalProvider(
        LocalAppColorScheme provides colorScheme,
        LocalAppTypography provides typography,
        LocalAppShape provides shape,
        LocalAppSize provides size,
        LocalIndication provides rippleIndication,
    ) {
        content()
    }

}

object AppTheme {
    val colorScheme: AppColorScheme
        @Composable get() = LocalAppColorScheme.current

    val typography: AppTypography
        @Composable get() = LocalAppTypography.current

    val shape: AppShape
        @Composable get() = LocalAppShape.current

    val size: AppSize
        @Composable get() = LocalAppSize.current

}