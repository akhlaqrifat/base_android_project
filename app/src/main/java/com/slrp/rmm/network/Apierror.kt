package com.slrp.rmm.network

data class Apierror(
    val debugMessage: Any,
    val message: String,
    val status: String,
    val subErrors: Any,
    val timestamp: Int
)