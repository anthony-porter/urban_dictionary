package com.apsoftware.myapplication.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apsoftware.myapplication.models.DefinitionModel
import com.apsoftware.myapplication.networking.UdApiService


class DefinitionViewModel : ViewModel() {
    private val definitions: MutableLiveData<List<DefinitionModel>> by lazy {
        MutableLiveData<List<DefinitionModel>>().also {
            loadDefinitions()
        }
    }

    fun getDefinitions(): LiveData<List<DefinitionModel>> {
        return definitions
    }

    private fun loadDefinitions() {
        UdApiService.getDefinitions()
    }
}