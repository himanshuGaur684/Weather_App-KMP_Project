package gaur.himanshu.weatherapp.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gaur.himanshu.weatherapp.domain.model.Weather
import gaur.himanshu.weatherapp.domain.model.WeatherItem
import gaur.himanshu.weatherapp.domain.use_cases.GetWeatherDetailsUseCase
import gaur.himanshu.weatherapp.domain.use_cases.GetWeatherUseCase
import gaur.himanshu.weatherapp.utils.common
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getWeatherDetailsUseCase: GetWeatherDetailsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow().common()

    fun getWeather(lat: Double, lon: Double) = viewModelScope.launch {
        val response = getWeatherUseCase(lat, lon)
        _uiState.update { UiState(isLoading = true) }
        if (response.isSuccess) {
            _uiState.update { UiState(data = response.getOrNull()) }
        } else {
            _uiState.update { UiState(error = response.exceptionOrNull()?.message.toString()) }
        }
    }

    fun getWeatherDetails(lat: Double, lon: Double) = viewModelScope.launch {
        val response = getWeatherDetailsUseCase(lat, lon)
        print(response)
        _uiState.update { it.copy(weatherDetails = response.getOrNull()) }
    }


}


data class UiState(
    val isLoading: Boolean = false,
    val data: Weather? = null,
    val error: String = "",
    val weatherDetails: List<WeatherItem>? = null
)