package com.apsoftware.myapplication.models

import com.squareup.moshi.Json

const val EMPTY_STRING: String = ""

/**
 * Simple model for word definitions
 * Assumption: API is not well documented. It is hard to say which field are guaranteed to be non-null
 * Therefore, other than the definition and the word I'm making all of the fields nullable.`Erring on te side of caution
 * The major edge cases I'm worried about are words that were defined in the near the start of Urban Dictionary. Especially
 * words that may not have received as much attention
 * Set name annotation as a best practice. Would be necessary if obfuscating code
 */
data class DefinitionModel(

    @field:Json(name = "definition") val definition: String, // Not nullable and no default value. If the word is missing an exception should be thrown
    @field:Json(name = "permalink") val permalink: String? = null,
    @field:Json(name = "thumbs_up") val thumbs_up: Int? = null,
    @field:Json(name = "thumbs_down") val thumbs_down: Int? = null,
    @field:Json(name = "sound_urls") val sound_urls: List<String>? = null,
    @field:Json(name = "author") val author: String? = null,
    @field:Json(name = "defId") val defId: String? = null,
    @field:Json(name = "word") val word: String, // Not nullable and no default value. If the word is missing an exception should be thrown
    @field:Json(name = "written_on") val written_on: String? = null,
    @field:Json(name = "example") val example: String? = null,
    @field:Json(name = "current_vote") val current_vote: String? = null
)

