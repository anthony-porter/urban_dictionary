package com.apsoftware.myapplication.ViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apsoftware.myapplication.models.Definition
import com.apsoftware.myapplication.networking.DefinitionApi
import com.apsoftware.myapplication.networking.DefinitionRetrofitClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class DefinitionViewModel : ViewModel() {

    lateinit var definitionApi: DefinitionApi

    val definitionList: MutableLiveData<List<Definition>> = MutableLiveData()

    val searchTerm:MutableLiveData<String> = MutableLiveData()

    fun updateDefinitionList(searchTerm: String) {
        Log.d("TESTING_TAG", "updating dataDefinti")
        DefinitionRetrofitClient.makeCall(searchTerm)
    }


}