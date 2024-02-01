package com.example.exploretube.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import androidx.core.content.ContextCompat.getSystemService
import dagger.hilt.android.qualifiers.ApplicationContext


object Connectivity {
     fun isNetworkAvailable(context: Context): Boolean {
        // Get the ConnectivityManager service
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

        // Get the active network information
        val capabilities = connectivityManager?.getNetworkCapabilities(connectivityManager.activeNetwork)

        // Check if the active network is not null and is connected
        return capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ?: false
    }
}