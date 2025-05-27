package ru.tinkoff.lunch.di.network

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.tinkoff.lunch.network.api.auth.AuthApi
import ru.tinkoff.lunch.network.api.auth.repository.AuthRepository
import ru.tinkoff.lunch.network.api.events.LunchEventsApi
import ru.tinkoff.lunch.network.api.events.pagination.LunchEventsSource
import ru.tinkoff.lunch.network.api.events.repository.LunchEventsRepository
import ru.tinkoff.lunch.network.api.events.repository.LunchEventsRepositoryImpl
import ru.tinkoff.lunch.network.common.authenticator.AuthAuthenticator
import ru.tinkoff.lunch.network.common.interceptor.AuthInterceptor
import ru.tinkoff.lunch.network.common.token.JwtTokenDataStore
import ru.tinkoff.lunch.network.common.token.JwtTokenManager
import ru.tinkoff.lunch.network.common.user_info.UserInfoDataStore
import ru.tinkoff.lunch.network.common.user_info.UserInfoManager
import javax.inject.Singleton

private const val USER_PREFERENCES = "user_preferences"
private const val BASE_URL = "http://10.0.2.2:8080/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providePreferencesDataStore(
        @ApplicationContext appContext: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { appContext.preferencesDataStoreFile(USER_PREFERENCES) }
        )
    }

    @Provides
    @Singleton
    fun provideJwtTokenManager(
        dataStore: DataStore<Preferences>
    ): JwtTokenManager {
        return JwtTokenDataStore(dataStore = dataStore)
    }

    @Provides
    @Singleton
    fun provideUserInfoManager(
        dataStore: DataStore<Preferences>
    ): UserInfoManager {
        return UserInfoDataStore(dataStore)
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(tokenManager: JwtTokenManager): AuthInterceptor {
        return AuthInterceptor(tokenManager)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        authAuthenticator: AuthAuthenticator,
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .authenticator(authAuthenticator)
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthApi(
        okHttpClient: OkHttpClient,
    ): AuthApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(AuthApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthRepository(
        authApi: AuthApi,
    ): AuthRepository {
        return AuthRepository(authApi = authApi)
    }

    @Singleton
    @Provides
    fun provideLunchesApi(
        okHttpClient: OkHttpClient,
    ): LunchEventsApi {
        return Retrofit.Builder()
          .baseUrl(BASE_URL)
          .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
          .addConverterFactory(GsonConverterFactory.create())
          .client(okHttpClient)
          .build()
          .create(LunchEventsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideLunchEventsSource(
        api: LunchEventsApi,
        userInfoManager: UserInfoManager,
    ): LunchEventsSource {
        return LunchEventsSource(
            api = api,
            userInfoManager = userInfoManager,
        )
    }

    @Singleton
    @Provides
    fun provideLunchesRepository(
        lunchEventsSource: LunchEventsSource,
    ): LunchEventsRepository {
        return LunchEventsRepositoryImpl(lunchEventsSource = lunchEventsSource)
    }
}
