package com.apsoftware.urbandictionary.data.network

import androidx.lifecycle.MutableLiveData
import com.apsoftware.urbandictionary.myapplication.models.Definition

object UrbanDictionaryDataSource {

    private lateinit var downloadedDefinitions: List<Definition>

    fun getDownloadedDefinition(): List<Definition> {
        return downloadedDefinitions;
    }

    fun getDefinition(word: String) {

    }
}