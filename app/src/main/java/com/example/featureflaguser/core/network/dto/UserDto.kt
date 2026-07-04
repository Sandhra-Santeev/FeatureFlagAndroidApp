package com.example.featureflaguser.core.network.dto


data class LoginResponse(
    val existingUser: UserDto,//object composition
    val token: String
)
data class UserDto (
    val _id: String,
    val email: String,
    val role: String,
    val organizationId: String

)
