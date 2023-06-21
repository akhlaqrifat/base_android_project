package com.slrp.rmm.network

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers

fun <A> performGetOperation(
    context: Context,
    networkCall: suspend () -> Resource<A>): LiveData<Resource<A>> =
    liveData(Dispatchers.IO) {

        if (!NetworkStatus.getInstance().isNetworkAvailable(context)) {
            emit(Resource.noInternet("No internet connection!!"))
            return@liveData
        }

        emit(Resource.loading())
        val responseStatus = networkCall.invoke()

        when (responseStatus.status) {

            Resource.Status.SUCCESS -> emit(Resource.success(responseStatus.data!!))
            Resource.Status.ERROR -> {
                emit(Resource.error("Data error",responseStatus.data))
            }

            else -> emit(Resource.noInternet(responseStatus.message!!, null))
        }
    }