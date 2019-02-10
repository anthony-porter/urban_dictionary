package com.apsoftware.myapplication.models

import com.squareup.moshi.Json


data class ApiResponse(
    @field:Json(name = "list") val list: List<Definition>
)
