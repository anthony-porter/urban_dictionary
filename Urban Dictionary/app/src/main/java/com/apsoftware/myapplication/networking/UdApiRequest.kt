package com.apsoftware.myapplication.networking

import com.apsoftware.myapplication.models.DefinitionModel
import retrofit2.Call
import retrofit2.http.GET

public interface UdApiRequest {
    @GET("define")
    fun getDefinitions(): Call<DefinitionModel>
}