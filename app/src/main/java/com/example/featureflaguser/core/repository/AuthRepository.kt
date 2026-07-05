package com.example.featureflaguser.core.repository

import com.example.featureflaguser.core.common.Result
import com.example.featureflaguser.core.datastore.DataStoreManager
import com.example.featureflaguser.core.network.ApiService
import com.example.featureflaguser.core.network.dto.LoginRequest
import com.example.featureflaguser.core.network.dto.LoginResponse
import javax.inject.Inject

class AuthRepository @Inject constructor(private val apiService: ApiService, private val dataStoreManager: DataStoreManager) {
    suspend fun login(request: LoginRequest):Result<LoginResponse> {
        try{
            val response = apiService.login(request)
            if(response.isSuccessful){
                val body = response.body()
                if(body!=null){
                    dataStoreManager.saveToken(body.token)
                    return Result.Success(body)
                }
                return Result.Error("Empty response from server")

            }
            return Result.Error("Login Failed")
        }catch (e: Exception){
            return Result.Error(e.message?:"Something went wrong")
        }


    }

}