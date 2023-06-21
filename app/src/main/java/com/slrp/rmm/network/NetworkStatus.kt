package com.slrp.rmm.network

import android.content.Context
import android.net.ConnectivityManager

class NetworkStatus private constructor(){

    //Single tone pattern
    companion object {
        private var single = NetworkStatus()
        fun getInstance(): NetworkStatus {
            return single
        }
    }
    //-----------------------

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnected
    }
}