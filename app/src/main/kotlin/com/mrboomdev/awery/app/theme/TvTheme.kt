package com.mrboomdev.awery.app.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.tv.material3.MaterialTheme as TvMaterialTheme
import androidx.tv.material3.darkColorScheme
import androidx.tv.material3.lightColorScheme
import com.mrboomdev.awery.ui.Themes
import com.mrboomdev.awery.app.App.Companion.isTv
import com.mrboomdev.awery.AwerySettings

@Composable
fun TvTheme(
	palette: AwerySettings.ThemeColorPaletteValue,
	isDark: Boolean,
	isAmoled: Boolean,
	content: @Composable () -> Unit
) {
	TvMaterialTheme(
		colorScheme = when(palette) {
			AwerySettings.ThemeColorPaletteValue.MATERIAL_YOU -> {
				if(isTv) {
					throw UnsupportedOperationException("How did you even done that?")
				}

				darkColorScheme()
			}

			else -> when(palette) {
				AwerySettings.ThemeColorPaletteValue.GREEN -> Themes.Green
				AwerySettings.ThemeColorPaletteValue.BLUE -> Themes.Blue
				else -> Themes.Red
			}.let { if(isDark || isTv) {
				darkColorScheme(
					surface = if(isAmoled) Color.Black else it.dark.background,
					background = if(isAmoled) Color.Black else it.dark.background
				)
			} else {
				lightColorScheme(
					surface = it.light.background,
					background = it.light.background
				)
			}}
		}
	) {
		content()
	}
}