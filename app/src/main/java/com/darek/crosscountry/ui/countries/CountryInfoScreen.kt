package com.darek.crosscountry.ui.countries

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.darek.crosscountry.data.models.CountryInfo
import com.darek.crosscountry.ui.theme.CrossCountryTheme

@Composable
internal fun CountryInfoScreen(
    countryName: String,
    viewModel: CountryInfoViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiCountryInfoState.collectAsStateWithLifecycle()

    LaunchedEffect(countryName) {
        viewModel.sendIntent(CountryInfoIntent.ScreenReady(countryName))
    }

    CountryInfoScreenContent(
        uiState = uiState,
    )
}

@Composable
internal fun CountryInfoScreenContent(uiState: UiCountryInfoState) {
    when (uiState) {
        UiCountryInfoState.Loading -> LoadingIndicator()
        is UiCountryInfoState.Success -> {
            CountryInfoLoadedContent(
                countryInfo = uiState.countryInfo,
            )
        }
        UiCountryInfoState.Error -> ErrorContent()
    }
}

@Composable
internal fun CountryInfoLoadedContent(countryInfo: CountryInfo) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = countryInfo.flag,
                fontSize = 150.sp,
                modifier = Modifier.padding(top = 40.dp),
            )
        }
        Column(
            modifier = Modifier.weight(1f),
        ) {
            Text(
                modifier = Modifier.padding(vertical = 16.dp).fillMaxWidth(),
                text = countryInfo.name,
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    InfoRow(label = "Capital", value = countryInfo.capital)
                    InfoRow(label = "Continent", value = countryInfo.continent)
                    InfoRow(label = "Population", value = countryInfo.population.toString())
                    InfoRow(label = "Area", value = "${countryInfo.area} km²")
                    InfoRow(label = "Timezone", value = countryInfo.timezone)
                    InfoRow(label = "Languages", value = countryInfo.languages)
                    InfoRow(label = "Car", value = countryInfo.carInfo)
                }
            }
        }
    }
}

@Composable
private fun InfoRow(
    label: String,
    value: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = label, fontWeight = FontWeight.Bold)
        Text(text = value)
    }
}

@Preview(showBackground = true)
@Composable
private fun CountryInfoScreenContentLoadedPreview() {
    CrossCountryTheme {
        CountryInfoScreenContent(
            UiCountryInfoState.Success(
                CountryInfo(
                    name = "Poland",
                    capital = "Warsaw",
                    population = 38000000L,
                    area = 312679.0,
                    continent = "Europe",
                    flag = "🇵🇱",
                    timezone = "UTC+01:00",
                    languages = "Polish",
                    carInfo = "PL, drive on the right",
                ),
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CountryInfoScreenContentErrorPreview() {
    CrossCountryTheme {
        CountryInfoScreenContent(
            UiCountryInfoState.Error,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CountryInfoScreenContentLoadingPreview() {
    CrossCountryTheme {
        CountryInfoScreenContent(
            UiCountryInfoState.Loading,
        )
    }
}
