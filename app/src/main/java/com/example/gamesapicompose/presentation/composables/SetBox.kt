package com.example.gamesapicompose.presentation.composables

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gamesapicompose.R

@Composable
fun SetBox(width: Float, height: Int, applyCorners: Boolean = false) {
    val shimmerAlpha = rememberInfiniteTransition(
        label = stringResource(R.string.shimmer_home)
    ).animateFloat(
        initialValue = 0f, targetValue = 1f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 600), repeatMode = RepeatMode.Reverse
        ), label = stringResource(R.string.shimmer_home)
    ).value
    Box(
        modifier = Modifier
            .height(height.dp)
            .fillMaxWidth(width)
            .background(
                Color.LightGray.copy(alpha = shimmerAlpha),
                shape = if (applyCorners) RoundedCornerShape(dimensionResource(R.dimen.size_10dp)) else RoundedCornerShape(
                    0.dp
                )
            )
    )
}