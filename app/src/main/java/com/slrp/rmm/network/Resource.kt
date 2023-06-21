package com.slrp.rmm.network

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status(val value: Int) {
        SUCCESS(200),
        ERROR(404),
        LOADING(410),
        NO_INTERNET(411),
        SERVER_ERROR(500)
    }


    companion object {

        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T?= null): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

        fun <T> noInternet(message: String, data: T? = null): Resource<T> {
            return Resource(Status.NO_INTERNET, data, message)
        }

        fun <T>serverError(message: String, data: T? = null):Resource<T>{
            return Resource(Status.SERVER_ERROR, data, message)
        }

    }


}