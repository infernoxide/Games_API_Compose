package com.example.gamesapicompose.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.gamesapicompose.R

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
            Column {
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