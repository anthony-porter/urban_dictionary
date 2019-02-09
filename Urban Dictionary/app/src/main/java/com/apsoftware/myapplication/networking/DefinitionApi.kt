package com.apsoftware.myapplication.networking

import com.apsoftware.myapplication.models.ApiResponse
import com.apsoftware.myapplication.models.Definition
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface DefinitionApi{
    @Headers("X-RapidAPI-Key:e0460dcc5cmsh8fcfc20ecda5985p1fb4ccjsnab95420b1737")
    @GET("define")
    fun getDefinitions(@Query("term")word: String?): Call<ApiResponse>
}