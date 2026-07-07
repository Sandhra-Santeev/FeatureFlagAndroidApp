package com.example.featureflaguser.feature.dashboard.repository

import com.example.featureflaguser.core.common.Result
import com.example.featureflaguser.core.network.ApiService
import com.example.featureflaguser.feature.dashboard.model.FeatureEvaluationResponse
import javax.inject.Inject

class FeatureRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun evaluateFeature(key:String): Result<FeatureEvaluationResponse>{
        val response = apiService.evaluateFeature(key)
        if(response.isSuccessful&&response.body()!=null){
            return Result.Success(response.body()!!)
        }

        return Result.Error(response.errorBody()?.string()?:"Something went wrong")
    }

}