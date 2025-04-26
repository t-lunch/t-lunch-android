package ru.tinkoff.lunch.network.common.authenticator

// todo: https://proandroiddev.com/jwt-authentication-and-refresh-token-in-android-with-retrofit-interceptor-authenticator-da021f7f7534
//class AuthAuthenticator @Inject constructor(
//    private val tokenManager: TokenManager,
//): Authenticator {
//
//    override fun authenticate(route: Route?, response: Response): Request? {
//        val token = runBlocking {
//            tokenManager.getToken().first()
//        }
//        return runBlocking {
//            val newToken = getNewToken(token)
//
//            if (!newToken.isSuccessful || newToken.body() == null) { //Couldn't refresh the token, so restart the login process
//                tokenManager.deleteToken()
//            }
//
//            newToken.body()?.let {
//                tokenManager.saveToken(it.token)
//                response.request.newBuilder()
//                    .header("Authorization", "Bearer ${it.token}")
//                    .build()
//            }
//        }
//    }
//
//    private suspend fun getNewToken(refreshToken: String?): retrofit2.Response<LoginResponse> {
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://jwt-test-api.onrender.com/api/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient)
//            .build()
//        val service = retrofit.create(AuthApiService::class.java)
//        return service.refreshToken("Bearer $refreshToken")
//    }
//}
