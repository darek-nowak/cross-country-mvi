package com.darek.crosscountry.ui.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darek.crosscountry.data.CountriesRepository
import com.darek.crosscountry.data.models.Country
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CountriesIntent {
    object ScreenReady : CountriesIntent()
    object FetchCountries : CountriesIntent()
}

internal sealed class UiCountriesState {
    object Loading : UiCountriesState()
    data class Success(val countries: List<Country>) : UiCountriesState()
    object Error : UiCountriesState()
}


@HiltViewModel
internal class CountriesViewModel @Inject constructor(
    private val countriesRepository: CountriesRepository
) : ViewModel() {

    val _uiState = MutableStateFlow<UiCountriesState>(UiCountriesState.Loading)
    val uiState: StateFlow<UiCountriesState> = _uiState.asStateFlow()

    fun sendIntent(intent: CountriesIntent) {
        when (intent) {
            is CountriesIntent.ScreenReady -> fetchCountries()
            is CountriesIntent.FetchCountries -> fetchCountries()
        }
    }

    private fun fetchCountries() {
        _uiState.value = UiCountriesState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            runCatching { countriesRepository.getCountries() }
                .onSuccess { _uiState.value = UiCountriesState.Success(it) }
                .onFailure {
                    println("Error fetching countries: ${it.message}")
                    _uiState.value = UiCountriesState.Error
                }
        }
    }
}
