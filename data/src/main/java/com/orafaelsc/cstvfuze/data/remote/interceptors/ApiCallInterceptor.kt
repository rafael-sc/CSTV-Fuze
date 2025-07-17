package com.orafaelsc.cstvfuze.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ApiCallInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        try {
            chain.proceed(
                chain
                    .request()
                    .newBuilder()
                    .addHeader(
                        name = "Accept",
                        value = "application/json",
                    ).addHeader(
                        name = "Authorization",
                        value = "Bearer YAqDjdon0cKQkfEPOyZrOB8wneXfznhnek8dpBH8g39nmK_QunU",
                    ).build(),
            )
        } catch (e: IOException) {
            throw e
        }
}
