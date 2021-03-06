package com.apsoftware.myapplication.viewmodels

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apsoftware.myapplication.models.Definition
import com.apsoftware.myapplication.networking.UrbanDictionaryClient
import com.apsoftware.myapplication.ui.DefinitionListAdapter
import kotlin.properties.Delegates


const val TAG: String = "DefinitionViewModel"

/**
 * ViewModel used by MainActivity
 */
class DefinitionViewModel : ViewModel() {
    // ViewModel's interface with the UI
    private var adapter: DefinitionListAdapter by Delegates.notNull()
    private var mockAdapter: DefinitionListAdapter? = null

    // ViewModel's interface with the Model
    private var definitionClient: UrbanDictionaryClient by Delegates.notNull()

    // Property for tracking the visibility of the loading spinner
    val loading: ObservableInt
        get() = definitionClient.loading

    val enabled: ObservableBoolean
        get() = definitionClient.enabled

    // Property for populating and updating the RecycleView
    val definitionList: MutableLiveData<List<Definition>>
        get() = definitionClient.getDefinitions()

    /**
     * Initialize ViewAdapter and DefinitionRetroFit client
     * TODO use dagger to inject adapter and definitionClient
     */
    fun init() {
        Log.d(TAG, "Initializing adapter and UrbanDictionaryClient")
        if (mockAdapter == null) {
            adapter = DefinitionListAdapter(this)
            definitionClient = UrbanDictionaryClient()
        } else {
            adapter = mockAdapter as DefinitionListAdapter
        }
    }

    /**
     * Called by submit_button's onClick function. Calls client to search for word submitted by the user
     * @param searchTerm word submitted to the Urban Dictionary API
     */
    fun updateDefinitionList(searchTerm: String) {
        Log.d(TAG, "updating dataDefinition for searchTerm: $searchTerm")
        definitionClient.makeCall(searchTerm)
    }

    /**
     * Binding ListAdapter to DefinitionsList
     * @param definitions for ListAdapter to bind to
     */
    fun setDefinitionsInAdapter(definitions: List<Definition>) {
        Log.d(TAG, "updating definition list")
        this.adapter.setDefinitions(definitions)
        this.adapter.notifyDataSetChanged()
    }

    /**
     * Used to bind RecyclerView to DefinitionListAdapter
     */
    fun getDefinitionListAdapter(): DefinitionListAdapter {
        return adapter
    }

    /**
     * Used to bind ViewHolders to ListItems in Definitions MutableLiveData
     * @param position of the corresponding ListItem to the ViewHolder
     * @return Definition to bind with ViewHolder
     */
    fun getDefinitionByIndex(position: Int?): Definition? {
        return if (definitionList.value != null &&
            position != null &&
            definitionList.value!!.size > position
        ) {
            definitionList.value!![position]
        } else null
    }

    /**
     * Interface for the UI to tell Model to sort data
     * @param isChecked status of sort button
     */
    fun sortRecyclerView(isChecked: Boolean) {
        definitionClient.sortRecyclerView(isChecked)
    }

    fun setMockAdapter(mock: DefinitionListAdapter) {
        mockAdapter = mock
    }
}