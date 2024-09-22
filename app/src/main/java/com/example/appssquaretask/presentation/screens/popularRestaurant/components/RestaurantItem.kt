package com.example.appssquaretask.presentation.screens.popularRestaurant.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.appssquaretask.R
import com.example.appssquaretask.data.model.city.Restaurant
import com.example.appssquaretask.presentation.theme.AppsSquareTaskTheme
import com.example.appssquaretask.presentation.theme.onPrimary
import com.example.appssquaretask.presentation.theme.secondary


@Composable
fun RestaurantItem(
    restaurant: Restaurant,
    onRestaurantClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 10.dp
            ),
            modifier = Modifier
                .padding(14.dp)
                .clickable { onRestaurantClicked() },
        ) {
            AsyncImage(
                model = restaurant.image,
                contentDescription = stringResource(id = R.string.city_image),
                modifier = Modifier.size(210.dp, 177.dp)
            )
        }

        Column(
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = restaurant.name,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Start,
                color = onPrimary,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "${restaurant.address} Reviews",
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center,
                color = secondary,
                modifier = Modifier.padding(start = 40.dp)
            )
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun RestaurantItemPreview() {
    AppsSquareTaskTheme {
        RestaurantItem(
            restaurant = Restaurant(
                name = "Cairo",
                address = "Cairo, Egypt",
                image = "https://www.google.com",
                id = 1,
                products = emptyList(),
                restaurantLat = 0.0.toString(),
                restaurantLong = 0.0.toString()
            )
        ) {}
    }
}