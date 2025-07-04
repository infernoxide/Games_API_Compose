package com.example.gamesapicompose.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.gamesapicompose.R

@Composable
fun MetascoreCard(metascore:Int){
    Card(
        modifier = Modifier.padding(dimensionResource(R.dimen.size_16dp)),
        shape = RoundedCornerShape(dimensionResource(R.dimen.size_8dp)),
        colors = CardDefaults.cardColors(
            containerColor = setCardColor(metascore)
        )
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.size_16dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = metascore.toString(),
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = dimensionResource(R.dimen.size_50dp).value.sp
            )
        }
    }
}

@Composable
fun setCardColor(metascore: Int): Color {
    return when(metascore){
        in 80..100 -> colorResource(R.color.green_radiant)
        in 60..79 -> colorResource(R.color.yellow_radiant)
        in 0..59 -> colorResource(R.color.red_radiant)
        else -> colorResource(R.color.black)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MetascoreCard(50)
}