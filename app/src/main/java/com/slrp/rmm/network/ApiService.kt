package com.slrp.rmm.network

import com.slrp.rmm.module.login.data.LoginRequest
import com.slrp.rmm.module.login.data.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {

    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

}