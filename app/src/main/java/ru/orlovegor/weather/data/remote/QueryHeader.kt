package ru.orlovegor.weather.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class QueryHeader(
    private val queryName: String,
    private val queryHeader: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url
        val url = originalUrl.newBuilder().addQueryParameter(
            queryName,
            queryHeader
        )
            .build()
        val modifiedRequest =
            originalRequest.newBuilder()
                .url(url)
                .build()
        return chain.proceed(modifiedRequest)
    }

}