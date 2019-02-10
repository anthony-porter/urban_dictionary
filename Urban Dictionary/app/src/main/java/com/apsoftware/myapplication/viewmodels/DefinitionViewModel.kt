package com.apsoftware.myapplication.viewmodels

import android.util.Log
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apsoftware.myapplication.models.Definition
import com.apsoftware.myapplication.networking.DefinitionRetrofitClient
import com.apsoftware.myapplication.ui.DefinitionListAdapter
import kotlin.properties.Delegates

const val TAG: String = "DefinitionViewModel"

class DefinitionViewModel : ViewModel() {
    private var adapter: DefinitionListAdapter by Delegates.notNull()
    var definitionClient: DefinitionRetrofitClient by Delegates.notNull()
        private set
    private var definitionClient: DefinitionRetrofitClient by Delegates.notNull()
    val loading: ObservableInt
        get() = definitionClient.loading
    val definitionList: MutableLiveData<List<Definition>>
        get() = definitionClient.getDefinitions()

    fun init() {
        Log.d(TAG, "Initializing adapter and DefinitionRetrofitClient")
        adapter = DefinitionListAdapter(R.layout.definition_view, this)
        definitionClient = DefinitionRetrofitClient()
    }

    fun updateDefinitionList(searchTerm: String) {
        Log.d(TAG, "updating dataDefinition for searchTerm: " + searchTerm)
        definitionClient.makeCall(searchTerm)
    }

    fun setDefinitionsInAdapter(definitions: List<Definition>) {
        Log.d(TAG, "updating definition list")
        this.adapter.setDefinitions(definitions)
        this.adapter.notifyDataSetChanged()
    }

    fun getDefinitionListAdapter(): DefinitionListAdapter {
        return adapter;
    }

    fun getDefinitionByIndex(position: Int?): Definition? {
        return if (definitionList.value != null &&
            position != null &&
            definitionList.value!!.size > position
        ) {
            definitionList.value!![position]
        } else null
    }


}