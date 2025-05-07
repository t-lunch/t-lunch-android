package ru.tinkoff.lunch.network.common.interceptor

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import ru.tinkoff.lunch.network.common.token.JwtTokenManager
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: JwtTokenManager,
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { tokenManager.getAccessJwt() }
        val request = chain.request().newBuilder()
        request.addHeader("Authorization", "Bearer $token")
        return chain.proceed(request.build())
    }
}
