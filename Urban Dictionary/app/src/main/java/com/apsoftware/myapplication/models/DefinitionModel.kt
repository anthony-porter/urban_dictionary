package com.apsoftware.myapplication.models

const val EMPTY_STRING: String = ""

/**
 * Simple model for word definitions
 * Assumption: API is not well documented. It is hard to say which field are guaranteed to be non-null
 * Therefore, other than the definition and the word I'm making all of the fields nullable.`Erring on te side of caution
 * The major edge cases I'm worried about are words that were defined in the near the start of Urban Dictionary. Especially
 * words that may not have received as much attention
 */
data class DefinitionModel(

    val definition: String, // Not nullable and no default value. If the word is missing an exception should be thrown
    val permalink: String? = null,
    val thumbs_up: Int? = null,
    val thumbs_down: Int? = null,
    val sound_urls: List<String>? = null,
    val author: String? = null,
    val defId: String? = null,
    val word: String, // Not nullable and no default value. If the word is missing an exception should be thrown
    val written_on: String? = null,
    val example: String? = null,
    val current_vote: String? = null
)

