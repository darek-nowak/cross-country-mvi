package com.darek.crosscountry.ui.countries

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    viewModel: CountryInfoViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiCountryInfoState.collectAsStateWithLifecycle()

    LaunchedEffect(countryName) {
        viewModel.sendIntent(CountryInfoIntent.ScreenReady(countryName))
    }

    CountryInfoContent(
        uiState = uiState
    )
}

@Composable
internal fun CountryInfoContent(
    uiState: UiCountryInfoState
) {
    when (uiState) {
        UiCountryInfoState.Loading -> LoadingIndicator()
        is UiCountryInfoState.Success -> {
            CountryInfoLoadedContent(
                countryInfo = uiState.countryInfo
            )
        }
        UiCountryInfoState.Error -> ErrorContent()
    }
}

@Composable
internal fun CountryInfoLoadedContent(
    countryInfo: CountryInfo
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
                    text = countryInfo.flag,
                    fontSize = 150.sp
                )
            }
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.TopCenter
            ) {
                Text(
                    text = countryInfo.name,
                    fontSize = 30.sp,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CountryInfoContentLoadedPreview() {
    CrossCountryTheme {
        CountryInfoContent(
            UiCountryInfoState.Success(
                CountryInfo(
                    name = "Poland",
                    capital = "Warsaw",
                    population = 38000000L,
                    area = 312679.0,
                    continent = "Europe",
                    flag = "🇵🇱",
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CountryInfoContentErrorPreview() {
    CrossCountryTheme {
        CountryInfoContent(
            UiCountryInfoState.Error
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CountryInfoContentLoadingPreview() {
    CrossCountryTheme {
        CountryInfoContent(
            UiCountryInfoState.Loading
        )
    }
}