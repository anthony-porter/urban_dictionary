package com.apsoftware.myapplication.models

import com.squareup.moshi.Json

data class ApiResponse(
    // Not nullable and no default value. If the word is missing an exception should be thrown
    @field:Json(name = "list") val list: List<Definition>
)
