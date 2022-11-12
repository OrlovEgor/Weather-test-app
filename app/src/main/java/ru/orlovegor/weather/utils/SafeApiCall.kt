package ru.orlovegor.weather.utils

import android.util.Log
import retrofit2.Response
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

private fun <T> error(message: String): ResultWrapper<T> {
    Log.d("Error", "Error message  = $message")
    return ResultWrapper.Error("Network call has failed for a following reason: $message")
}
