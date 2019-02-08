package com.apsoftware.urbandictionary.data.network

import com.apsoftware.urbandictionary.myapplication.models.Definition
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface DefinitionApiRequest {
    @GET("define")
    fun getDefinitions(@Query("term")word: String?): Single<List<Definition>>
}