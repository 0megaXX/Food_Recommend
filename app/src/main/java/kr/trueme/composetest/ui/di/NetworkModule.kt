package kr.trueme.composetest.ui.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okio.Timeout
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


//    @Provides
//    @Singleton
//    fun provideOkHttpClient(
//        interceptor: Interceptor,
//        authenticator: Authenticator,
//    ): OkHttpClient {
//        return createOkHttpClient(interceptor, authenticator)
//    }
//
//    private fun createOkHttpClient(
//        interceptor: Interceptor?,
//        authenticator: Authenticator?,
//    ): OkHttpClient {
//        val client = OkHttpClient.Builder()
//        if (authenticator != null) client.authenticator(authenticator)
//        if (interceptor != null) client.addInterceptor(interceptor)
////        val httpLoggingInterceptor =
////            HttpLoggingInterceptor { message -> Timber.d("%s", message) }
////        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//        return client
//            .connectTimeout(timeout_connect, TimeUnit.SECONDS)
//            .readTimeout(timeout_read, TimeUnit.SECONDS)
//            .writeTimeout(timeout_write, TimeUnit.SECONDS)
//            .build()
//    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api")
//            .addConverterFactory(
//                JacksonConverterFactory.create(
//                    jacksonObjectMapper()
//                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//                        .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true)
//                        .registerModule(kotlinModule())
//                        .registerModule(JavaTimeModule())
//                )
//            )
            .build()
    }
}