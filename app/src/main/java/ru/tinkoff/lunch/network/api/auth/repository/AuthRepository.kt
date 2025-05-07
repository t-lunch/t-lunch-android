package ru.tinkoff.lunch.network.api.auth.repository

import com.skydoves.sandwich.ApiResponse
import ru.tinkoff.lunch.network.api.auth.AuthApi
import ru.tinkoff.lunch.network.api.auth.model.login.LoginBody
import ru.tinkoff.lunch.network.api.auth.model.login.LoginResponse
import ru.tinkoff.lunch.network.api.auth.model.refresh.RefreshBody
import ru.tinkoff.lunch.network.api.auth.model.refresh.RefreshResponse
import ru.tinkoff.lunch.network.api.auth.model.signup.SignUpBody
import ru.tinkoff.lunch.network.api.auth.model.signup.SignUpResponse

class AuthRepository(
    private val authApi: AuthApi,
) {

    suspend fun signUp(body: SignUpBody): ApiResponse<SignUpResponse> {
        return authApi.signUp(body = body)
    }

    suspend fun login(body: LoginBody): ApiResponse<LoginResponse> {
        return authApi.login(body = body)
    }

    suspend fun refresh(body: RefreshBody): ApiResponse<RefreshResponse> {
        return authApi.refresh(body = body)
    }
}
