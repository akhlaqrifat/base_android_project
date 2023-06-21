package com.slrp.rmm.network

import android.util.Log
import com.google.gson.Gson
import java.io.Reader

object JSONUtil {
    fun getErrorObjectFromString(errorBody: Reader): ErrorModel? {
        var error = Gson().fromJson(errorBody,ErrorModel::class.java)
        Log.e(JSONUtil::class.simpleName,error.toString())
        return error
    }
}
