package gaur.himanshu.weatherapp.ui.permission

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AndroidLocationService(
    private val context: Context,
    private val launcher: ActivityResultLauncher<String>
) : LocationService {

    private val fusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    override fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    @Suppress("MissingPermission")
    suspend fun getLocation(): Location = suspendCoroutine { continuation ->
        fusedLocationProviderClient?.lastLocation?.addOnSuccessListener { location ->
           location?.let {
               continuation.resumeWith(
                   Result.success(
                       Location(
                           location.latitude,
                           location.longitude
                       )
                   )
               )
           }

        }?.addOnFailureListener {
            continuation.resumeWithException(Exception("Unable to get location."))
        }
    }

    override fun requestLocationPermission(granted: (Boolean) -> Unit) {
        launcher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
    }
}