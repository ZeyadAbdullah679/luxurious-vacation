package com.example.appssquaretask.presentation.screens.popularRestaurant

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.appssquaretask.R
import com.example.appssquaretask.presentation.screens.popularRestaurant.components.RestaurantItem
import com.example.appssquaretask.presentation.theme.AppsSquareTaskTheme
import com.example.appssquaretask.presentation.theme.background
import com.example.appssquaretask.presentation.theme.onPrimary
import com.example.appssquaretask.presentation.utils.rememberFlowWithLifecycle

@Composable
fun PopularRestaurantsScreen(
    viewModel: PopularRestaurantViewModel = hiltViewModel(),
    onRestaurantClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val effect = rememberFlowWithLifecycle(viewModel.effect)
    val context = LocalContext.current

    LaunchedEffect(effect) {
        effect.collect { action ->
            when (action) {
                is PopularRestaurantsReducer.Effect.Error -> {
                    Toast.makeText(context, action.message, Toast.LENGTH_SHORT).show()
                }

                is PopularRestaurantsReducer.Effect.NavigateToRestaurantDetails -> {
                    onRestaurantClick(action.restaurantId)
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getPopularRestaurants()
    }

    PopularRestaurantsScreenContent(
        state = state,
        onEvent = viewModel::sendEventForEffect
    )

}

@Composable
private fun PopularRestaurantsScreenContent(
    state: PopularRestaurantsReducer.State,
    onEvent: (PopularRestaurantsReducer.Event) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = stringResource(R.string.app_logo),
                modifier = Modifier.size(136.dp, 117.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            Text(
                text = stringResource(R.string.popular_restaurants),
                style = MaterialTheme.typography.titleLarge,
                color = onPrimary,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        itemsIndexed(state.restaurants) { index, restaurant ->
            RestaurantItem(
                restaurant = restaurant,
                onRestaurantClicked = {
                    onEvent(
                        PopularRestaurantsReducer.Event.RestaurantClicked(
                            index
                        )
                    )
                }
            )
            if (restaurant != state.restaurants.last()) {
                Spacer(modifier = Modifier.height(26.dp))
            }
        }
    }

}


@Preview(
    showSystemUi = true,
)
@Composable
private fun PopularCitiesScreenPreview() {
    AppsSquareTaskTheme {
        PopularRestaurantsScreen {}
    }
}