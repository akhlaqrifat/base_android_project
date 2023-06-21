package com.slrp.rmm.network

import android.util.Log
import retrofit2.Response

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<*> {

        try {

            val response = call()
            when(response.code()){

                Resource.Status.SUCCESS.value -> {
                    val body = response.body()
                    if (body != null) return Resource.success(body)
                }

                Resource.Status.ERROR.value -> {
                    Log.e(BaseDataSource::class.simpleName," error status found\n"+response)
                    val errorBody = JSONUtil.getErrorObjectFromString(response.errorBody()!!.charStream())
                    val res = Resource.error(errorBody!!.apierror.message, errorBody)
                    return res
                }
                Resource.Status.SERVER_ERROR.value -> {
                    val errorBody = JSONUtil.getErrorObjectFromString(response.errorBody()!!.charStream())
                    return Resource.serverError(errorBody!!.apierror.message, errorBody)
                }


            }

        } catch (e: Exception) {
            Log.d("TAG", "getResult: " + e.printStackTrace())
            return Resource.noInternet("Exception ",e)
        }

        return Resource.noInternet("Exception ","No Internet")
    }

    private fun <T> error(): Resource<T> {
        return Resource.noInternet("")
    }
}