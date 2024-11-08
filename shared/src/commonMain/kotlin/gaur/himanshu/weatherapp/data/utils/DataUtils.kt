package gaur.himanshu.weatherapp.data.utils

import gaur.himanshu.weatherapp.data.mappers.formatFloatUpToTwoDecimalPlaces
import gaur.himanshu.weatherapp.utils.dateFormatter

//"https://openweathermap.org/img/wn/" + weatherViewModelWrapper.uiState.data!.icon + "@2x.png"
object DataUtils {

    fun getImageUrl(iconId: String) = "https://openweathermap.org/img/wn/$iconId@2x.png"

    fun convertDate(date: String) : String{
       return dateFormatter(date.toLong())
    }

    fun floatUpToTwoDecimalPlaces(float:Float):String = formatFloatUpToTwoDecimalPlaces(float)
}
