package com.task.ratesapp.core.di

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.task.ratesapp.core.BuildConfig
import com.task.ratesapp.core.models.Response
import com.task.ratesapp.core.models.deserializer.ResponseDeserializer
import com.task.ratesapp.core.providers.IConfigurationProvider
import com.task.ratesapp.core.services.IRateAppRetorfitService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    single { provideOkHttpClient(get()) }
    single { provideHttpLoggingInterceptor() }
    single { provideRetrofit(get(), get()) }
    factory { provideRateAppRetrofitService(get()) }
}


fun provideRateAppRetrofitService(retrofit: Retrofit): IRateAppRetorfitService {
    return retrofit.create(IRateAppRetorfitService::class.java)
}

fun provideRetrofit(
    okHttpClient: OkHttpClient,
    provider: IConfigurationProvider
): Retrofit {
    val gson = GsonBuilder()
        .registerTypeAdapter(
            object : TypeToken<Response>() {}.type,
            ResponseDeserializer()
        )
        .enableComplexMapKeySerialization()
        .serializeNulls()
        .setPrettyPrinting()
        .setVersion(1.0)
        .create()
    return Retrofit.Builder()
        .baseUrl(provider.apiBaseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    val builder = OkHttpClient.Builder()
    builder.addInterceptor(httpLoggingInterceptor)

    return builder.build()
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = if (BuildConfig.DEBUG)
        HttpLoggingInterceptor.Level.BODY
    else
        HttpLoggingInterceptor.Level.NONE
    return loggingInterceptor
}