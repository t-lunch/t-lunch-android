package ru.tinkoff.lunch.network.api.auth

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST
import ru.tinkoff.lunch.network.api.auth.model.login.LoginBody
import ru.tinkoff.lunch.network.api.auth.model.login.LoginResponse
import ru.tinkoff.lunch.network.api.auth.model.refresh.RefreshBody
import ru.tinkoff.lunch.network.api.auth.model.refresh.RefreshResponse
import ru.tinkoff.lunch.network.api.auth.model.signup.SignUpBody
import ru.tinkoff.lunch.network.api.auth.model.signup.SignUpResponse

interface AuthApi {

    @POST("v1/signup")
    suspend fun signUp(
        @Body body: SignUpBody,
    ): ApiResponse<SignUpResponse>

    @POST("v1/login")
    suspend fun login(
        @Body body: LoginBody,
    ): ApiResponse<LoginResponse>

    @POST("v1/refresh")
    suspend fun refresh(
        @Body body: RefreshBody,
    ): ApiResponse<RefreshResponse>
}
