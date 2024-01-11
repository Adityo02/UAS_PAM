package com.example.outdoor.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.outdoor.R
import com.example.outdoor.navigasi.RouteMap

object GetStartedDestinasi : RouteMap {
    override val route = "laundry"
    override val tittleRes = R.string.app_name
}

@Composable
fun Homepage(
    onNextButtonClicked: () -> Unit = {}
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedCard(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Image(painter = painterResource(id = R.drawable.logo), contentDescription = "")
                Text(
                    text = "ADVENTURE",
                    color = Color.DarkGray,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 35.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)

                )
                Text(
                    text = "OUTDOOR",
                    color = Color.DarkGray,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 55.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(1f, false),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = onNextButtonClicked
            ) {
                Text(text = "Get Started")
            }
        }
    }
}