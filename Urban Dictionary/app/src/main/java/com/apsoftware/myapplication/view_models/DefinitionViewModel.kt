package com.apsoftware.myapplication.view_models

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apsoftware.myapplication.models.DefinitionModel
import com.apsoftware.myapplication.networking.UdApiService


class DefinitionViewModel(application: Application) : AndroidViewModel(application) {


    val defintionModelObservable: MutableLiveData<DefinitionModel> = UdApiService.getDefinitions()


    fun getDefinitinModelObservable(): LiveData<DefinitionModel> {
        return defintionModelObservable;
    }

    public val observableField: ObservableField<DefinitionViewModel> = ObservableField()


    /*private val definitions: MutableLiveData<List<DefinitionModel>> by lazy {
        MutableLiveData<List<DefinitionModel>>().also {
            loadDefinitions()
        }
    }

    fun getDefinitions(): LiveData<List<DefinitionModel>> {
        return definitions
    }

    private fun loadDefinitions() {
        UdApiService.getDefinitions()
    }*/
}