package com.apsoftware.myapplication.models

import com.squareup.moshi.Json


/**
 * Model utilized by Moshi for deserializing JSON response
 */
data class ApiResponse(
    @field:Json(name = "list") val list: List<Definition>
)
