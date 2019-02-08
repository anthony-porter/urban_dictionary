package com.apsoftware.urbandictionary.data

import androidx.lifecycle.LiveData
import com.apsoftware.urbandictionary.data.network.UrbanDictionaryDataSource
import com.apsoftware.urbandictionary.myapplication.models.Definition

object DefinitionRepository {
    fun deleteOldData(){}
    fun fetchData(word: String) {
        UrbanDictionaryDataSource.getDefinition(word)
    }
    val networkData: List<Definition> = UrbanDictionaryDataSource.getDownloadedDefinition()
}