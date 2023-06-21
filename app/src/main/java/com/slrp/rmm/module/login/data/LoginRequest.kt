package com.slrp.rmm.module.login.data

data class LoginRequest(
    val emailOrPhone: String,
    val password: String
)