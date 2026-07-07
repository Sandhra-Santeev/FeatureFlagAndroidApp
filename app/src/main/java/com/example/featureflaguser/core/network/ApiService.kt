package com.example.featureflaguser.core.network

import com.example.featureflaguser.core.common.Constants
import com.example.featureflaguser.core.network.dto.LoginRequest
import com.example.featureflaguser.core.network.dto.LoginResponse
import com.example.featureflaguser.feature.dashboard.model.FeatureEvaluationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST(Constants.LOGIN_PATH)
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET(Constants.EVALUATE_KEY)
    suspend fun evaluateFeature(
        @Path("key") key: String
    ): Response<FeatureEvaluationResponse>
}