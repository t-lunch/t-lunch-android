package ru.tinkoff.lunch.network.common.authenticator

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.getOrNull
import com.skydoves.sandwich.isSuccess
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.tinkoff.lunch.network.api.auth.AuthApi
import ru.tinkoff.lunch.network.api.auth.model.refresh.RefreshBody
import ru.tinkoff.lunch.network.api.auth.model.refresh.RefreshResponse
import ru.tinkoff.lunch.network.common.token.JwtTokenManager
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val tokenManager: JwtTokenManager,
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = runBlocking { tokenManager.getAccessJwt() }
        return runBlocking {
            val newToken = getNewToken(token)

            // Couldn't refresh the token, so restart the login process
            if (!newToken.isSuccess || newToken.getOrNull() == null) {
                tokenManager.removeAccessToken()
            }

            newToken.getOrNull()?.let {
                tokenManager.saveAccessJwt(it.accessToken)
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${it.accessToken}")
                    .build()
            }
        }
    }

    private suspend fun getNewToken(refreshToken: String?): ApiResponse<RefreshResponse> {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jwt-test-api.onrender.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        val api = retrofit.create(AuthApi::class.java)
        return api.refresh(body = RefreshBody(refreshToken = refreshToken))
    }
}
