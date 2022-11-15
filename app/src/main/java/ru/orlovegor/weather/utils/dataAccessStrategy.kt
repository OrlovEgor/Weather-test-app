package ru.orlovegor.weather.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException

 suspend fun<T> dataAccessStrategy (
    networkCall: suspend () -> T,
    saveCallResult: suspend (T) -> Unit,
    databaseQuery: suspend () -> T,
    coroutineDispatcher: CoroutineDispatcher
): UIState<T> {
    return withContext(coroutineDispatcher) {
        return@withContext try {
            val loadData = networkCall.invoke()
            saveCallResult.invoke(loadData)
            UIState.Success(loadData)
        } catch (i: IOException) {
            try {
                val databaseData = databaseQuery.invoke()
                UIState.NetworkError(i.message.toString(), databaseData)
            } catch (t: Throwable) {
                UIState.Error(t.message.toString())
            }
        }
    }
}