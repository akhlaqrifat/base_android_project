package com.slrp.rmm.network

import com.slrp.rmm.module.login.data.LoginRequest
import javax.inject.Inject

class ApiDataSource @Inject constructor(private val apiService: ApiService) : BaseDataSource() {
    suspend fun login(loginRequest: LoginRequest) = getResult(call =  { apiService.login(loginRequest) } )
}