package com.sahalnazar.paging3_retrofit_roomdb.data.remote

import com.sahalnazar.paging3_retrofit_roomdb.util.ApiKey
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("api_key", ApiKey.theMovieDbKey) // Todo: Add your api key here
            .build()
        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}