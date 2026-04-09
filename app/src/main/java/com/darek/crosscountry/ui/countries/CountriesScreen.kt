package com.darek.crosscountry.ui.countries

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.darek.crosscountry.data.models.Country
import com.darek.crosscountry.ui.theme.CrossCountryTheme

@Composable
internal fun CountriesScreen(
    viewModel: CountriesViewModel = hiltViewModel(),
    onCountryClick: (String) -> Unit = {}
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sendIntent(CountriesIntent.ScreenReady)
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CountriesScreenContent(
            countriesState = uiState.value,
            onCountryClick = onCountryClick
        )
    }
}

@Composable
internal fun CountriesScreenContent(
    countriesState: UiCountriesState,
    onCountryClick: (String) -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (countriesState) {
            is UiCountriesState.Loading -> LoadingIndicator()
            is UiCountriesState.Success -> {
                CountryList(
                    countries = countriesState.countries,
                    onCountryClick = onCountryClick
                )
            }
            is UiCountriesState.Error -> ErrorContent()
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
internal fun ErrorContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Error")
    }
}

@Composable
internal fun CountryList(
    countries: List<Country>,
    onCountryClick: (String) -> Unit = {}
) {
    LazyColumn {
        items(countries) { country ->
            CountryItem(country, onCountryClick)
        }
    }
}

@Composable
internal fun CountryItem(
    country: Country,
    onCountryClick: (String) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCountryClick(country.name) }
            .padding(horizontal = 25.dp, vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = country.name)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = country.flag, fontSize = 25.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun CountriesScreenContentLoadingPreview() {
    CrossCountryTheme {
        CountriesScreenContent(
            countriesState = UiCountriesState.Loading
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CountriesScreenContentSuccessPreview() {
    CrossCountryTheme {
        CountriesScreenContent(
            countriesState = UiCountriesState.Success(
                countries = listOf(
                    Country("Poland", "🇵🇱"),
                    Country("Germany", "🇩🇪"),
                    Country("France",  "🇫🇷")
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CountriesScreenContentErrorPreview() {
    CrossCountryTheme {
        CountriesScreenContent(
            countriesState = UiCountriesState.Error
        )
    }
}
