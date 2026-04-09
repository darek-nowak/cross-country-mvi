package com.darek.crosscountry.ui.countries

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.darek.crosscountry.data.Country
import com.darek.crosscountry.data.CountryResponse
import com.darek.crosscountry.ui.CountriesIntent
import com.darek.crosscountry.ui.CountriesViewModel
import com.darek.crosscountry.ui.UiCountriesState
import com.darek.crosscountry.ui.theme.CrossCountryTheme

@Composable
internal fun CountriesScreen(viewModel: CountriesViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sendIntent(CountriesIntent.ScreenReady)
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when (uiState.value) {
            is UiCountriesState.Loading -> LoadingIndicator()
            is UiCountriesState.Success -> {
                val countries = (uiState.value as UiCountriesState.Success).countries
                CountryList(countries)
            }

            is UiCountriesState.Error -> Text(text = "Error")
        }
    }
}

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
internal fun CountryList(countries: List<Country>) {
    LazyColumn {
        items(countries) { country ->
            CountryItem(country)
        }
    }
}

@Composable
internal fun CountryItem(
    country: Country
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = country.name)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = country.flag)
    }
}

@Composable
internal fun CountryInfoContent(
    name: String,
    flag: String,
    capital: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = flag,
                    fontSize = 150.sp
                )
            }
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.TopCenter
            ) {
                Text(
                    text = name,
                    fontSize = 30.sp,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CountryInfoContentPreview() {
    CrossCountryTheme {
        CountryInfoContent(
            name = "Poland",
            flag = "🇵🇱",
            capital = "Warsaw"
        )
    }
}
