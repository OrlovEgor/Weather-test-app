package ru.orlovegor.weather.utils

import java.io.IOException

suspend fun <T> safeApiCall(call: suspend () -> T): ResultWrapper<T> {

    return try {
        ResultWrapper.Success(call.invoke())
    } catch (t: IOException) {
        ResultWrapper.NetworkError
    } catch (t: Throwable) {
        ResultWrapper.Error(t.message.orEmpty())
    }

}

