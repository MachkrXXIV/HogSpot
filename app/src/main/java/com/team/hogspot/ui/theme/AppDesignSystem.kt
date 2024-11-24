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
    val profileColors: List<Color> = emptyList()
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
    val button: Shape,
    val profileImage: Shape,
)


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
        profileColors = emptyList()
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
        button = RectangleShape,
        profileImage = RectangleShape,
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