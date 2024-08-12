package com.easyhz.noffice.core.network.api.auth

import com.easyhz.noffice.core.network.model.request.sign.LoginRequest
import com.easyhz.noffice.core.network.model.response.auth.UserResponse
import com.easyhz.noffice.core.network.util.NofficeResult
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/member/login")
    suspend fun login(
        @Body body: LoginRequest
    ): NofficeResult<UserResponse>
}