package com.example.featureflaguser.core.network

import com.example.featureflaguser.core.datastore.DataStoreManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : Interceptor
{
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            dataStoreManager.getToken().first()
        }
        val request = chain.request()
        val requestBuilder = request.newBuilder()
        if(!token.isNullOrBlank()){
            requestBuilder.header(
                "Authorization",
                "Bearer $token"
            )
        }
        val newRequest = requestBuilder.build()
        return chain.proceed(newRequest)

    }
}