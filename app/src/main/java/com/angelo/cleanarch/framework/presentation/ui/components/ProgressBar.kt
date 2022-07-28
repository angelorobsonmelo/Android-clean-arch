package com.angelo.cleanarch.framework.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProgressBar(
    modifier: Modifier = Modifier,
    visibility: Boolean = true,
    onProgressGone: @Composable () -> Unit = {}
) {
    AnimatedVisibility(visible = visibility) {
        CircularProgressIndicator(
            modifier = modifier
        )
    }

    if (visibility.not()) {
        onProgressGone.invoke()
    }
}