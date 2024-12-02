package com.team.hogspot.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

// AppDesignSystem.kt
// Defines the interfaces for the design system of the app

// colors
data class AppColorScheme(
    val primary: Color,
    val background: Color,
    val backgroundSecondary: Color,
    val onBackground: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textTertiary: Color,
    val profileColors: List<Color> = emptyList(),
    val difficultyEasy: Color = Color.Unspecified,
    val difficultyMedium: Color = Color.Unspecified,
    val difficultyHard: Color = Color.Unspecified,
    // add more to interface if needed
)


// typography
data class AppTypography (
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val p: TextStyle,
    val labelNormal: TextStyle,
    val labelSmall: TextStyle,
)


// shape
data class AppShape(
    val container: Shape,
    val containerSmall: Shape,
    val button: Shape,
    val profileImage: Shape,
    val containerRoundedTop: Shape,
    val containerRoundedBottom: Shape,
    val containerRoundedNone: Shape,
    val tag: Shape,
) {
}


// sizes
data class AppSize(
    val large: Dp,
    val medium: Dp,
    val normal: Dp,
    val small: Dp,
)


val  LocalAppColorScheme = staticCompositionLocalOf {
    AppColorScheme(
        primary = Color.Unspecified,
        background = Color.Unspecified,
        backgroundSecondary = Color.Unspecified,
        onBackground = Color.Unspecified,
        textPrimary = Color.Unspecified,
        textSecondary = Color.Unspecified,
        textTertiary = Color.Unspecified,
        profileColors = emptyList(),
        difficultyEasy = Color.Unspecified,
        difficultyMedium = Color.Unspecified,
        difficultyHard = Color.Unspecified,
    )
}

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        h1 = TextStyle.Default,
        h2 = TextStyle.Default,
        h3 = TextStyle.Default,
        p = TextStyle.Default,
        labelNormal = TextStyle.Default,
        labelSmall = TextStyle.Default,
    )
}

val LocalAppShape = staticCompositionLocalOf {
    AppShape(
        container = RectangleShape,
        containerSmall = RectangleShape,
        button = RectangleShape,
        profileImage = RectangleShape,
        containerRoundedTop = RectangleShape,
        containerRoundedBottom = RectangleShape,
        containerRoundedNone = RectangleShape,
        tag = RectangleShape,
    )
}

val LocalAppSize = staticCompositionLocalOf {
    AppSize(
        large = Dp.Unspecified,
        medium = Dp.Unspecified,
        normal = Dp.Unspecified,
        small = Dp.Unspecified,
    )
}