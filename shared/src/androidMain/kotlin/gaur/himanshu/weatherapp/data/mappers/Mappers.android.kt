package gaur.himanshu.weatherapp.data.mappers

import java.util.Locale

actual fun formatFloatUpToTwoDecimalPlaces(float: Float): String {
    return String.format(Locale.getDefault(),"%.2f",float)
}