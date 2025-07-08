package com.example.gamesapicompose.composables

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gamesapicompose.R

@Composable
fun SetSpace(height: Int) {
    Spacer(modifier = Modifier.height(height.dp))
}

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

@Composable
fun ShimmerHomeResults() {
    SetSpace(16)
    SetBox(1f, 35)
    SetSpace(16)
    repeat(2) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.size_10dp))
        ) {
            Column() {
                SetBox(1f, 250, true)
                SetSpace(16)
                SetBox(0.7f, 25)
                SetSpace(8)
            }
        }
    }
}

@Composable
fun ShimmerDetailView() {
    SetBox(1f, 250)
    SetSpace(10)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(R.dimen.size_10dp))
    ) {
        Row(modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.size_10dp))) {
            Column {
                SetBox(0.6f, 30)
                SetSpace(15)
                SetBox(0.4f, 30)
            }
            Column(modifier = Modifier.padding(start = dimensionResource(R.dimen.size_20dp))) {
                SetBox(0.9f, 90, true)
            }
        }
        Column(modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.size_10dp))) {
            SetSpace(20)
            SetBox(1f, 15)
            SetSpace(10)
            SetBox(1f, 15)
            SetSpace(10)
            SetBox(0.5f, 15)
        }
    }
}