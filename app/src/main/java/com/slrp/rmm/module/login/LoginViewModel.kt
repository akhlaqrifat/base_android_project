package com.slrp.rmm.module.login

import android.content.Context
import androidx.lifecycle.ViewModel
import com.slrp.rmm.module.login.data.LoginRequest
import com.slrp.rmm.network.ApiDataSource
import com.slrp.rmm.network.performGetOperation
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiDataSource: ApiDataSource,
    @ApplicationContext val context: Context
) : ViewModel()  {
    fun login(loginRequest: LoginRequest) = performGetOperation(context){apiDataSource.login(loginRequest)}
}