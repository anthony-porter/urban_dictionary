package com.apsoftware.urbandictionary.data.network

import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object RetrofitModule {
    const val BASE_URL: String = "https://mashape-community-urban-dictionary.p.mashape.com"
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideDefinitionApiRequest(retrofit: Retrofit): DefinitionApiRequest {
        return retrofit.create(DefinitionApiRequest::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

}