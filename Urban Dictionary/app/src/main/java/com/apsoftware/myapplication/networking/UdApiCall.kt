package com.apsoftware.myapplication.networking

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_URL: String = "https://mashape-community-urban-dictionary.p.mashape.com"

/**
 * Use retrofit to make api call. Use live data to update recycler view listitems
 */
@Module
object UdApiCall {

    @Provides
    fun getService(): UdApiRequest {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(UdApiRequest::class.java)
    }


}