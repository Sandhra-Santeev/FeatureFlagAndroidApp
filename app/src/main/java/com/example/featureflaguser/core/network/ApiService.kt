package com.example.featureflaguser.core.network

import com.example.featureflaguser.core.common.Constants
import com.example.featureflaguser.core.network.dto.LoginRequest
import com.example.featureflaguser.core.network.dto.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST(Constants.LOGIN_PATH)
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}