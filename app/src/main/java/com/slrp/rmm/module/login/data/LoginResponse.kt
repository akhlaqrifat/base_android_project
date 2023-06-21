package com.slrp.rmm.module.login.data

data class LoginResponse(
    val authToken: String,
    val message: String,
    val sessionTime: String,
    val userId: Int
)