package com.apsoftware.myapplication.networking

import android.util.Log
import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.apsoftware.myapplication.models.ApiResponse
import com.apsoftware.myapplication.models.Definition
import dagger.Component
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


const val BASE_URL: String = "https://mashape-community-urban-dictionary.p.mashape.com/"
const val TAG = "DefinitionRetrofitCl"

/**
 * This class acts as the model in the MVVM pattern. It is responsible for retrieving and holding data
 * while the app runs. Utilizing this patter also decreases the need to save instance state using
 * traditional methods.
 * TODO move progress spinner to a fragment
 */
@Component(modules = [DefinitionNetworkModule::class])
class DefinitionRetrofitClient : Callback<ApiResponse> {

    private val definitions: MutableLiveData<List<Definition>> = MutableLiveData()
    var loading: ObservableInt = ObservableInt(View.GONE)

    /**
     * Update the definitions MutableLiveData instance and hide the loading spinner
     * @param call call used to make request
     * @param response response from urban dictionary api
     */
    override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
        Log.d(TAG, "SUCCESS" + response.body())
        definitions.value = response.body()!!.list
        loading.set(View.GONE)
    }

    /**
     * Getter for accessing definitions MutableLiveData object. Used as entry point to update live data
     * @return Definitions MutableLiveData object
     */
    fun getDefinitions(): MutableLiveData<List<Definition>> {
        return definitions
    }

    /**
     * Respond to error use case
     * Hide loading spinner
     * //TODO add error handling
     */
    override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
        Log.e(TAG, "Api failure. reason: " + t.message)
        loading.set(View.GONE)
    }

    /**
     * Send a request to Urban Dicionary's API. Show loading spinner.
     * @param searchTerm term submitted by the user
     */
    fun makeCall(searchTerm: String) {
        val definitionApiCall = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build().create(DefinitionApi::class.java).getDefinitions(searchTerm).enqueue(this)
        Log.d(TAG, definitionApiCall.toString())
        loading.set(View.VISIBLE)
    }

    /**
     * Sort the recyclerview in descending order by either up-votes or down-votes
     * @param isChecked the state of the sort button
     */
    fun sortRecyclerView(isChecked: Boolean) {
        if (isChecked) {
            definitions.value = definitions.value!!.sortedWith(compareByDescending { it.thumbs_down })
        } else {
            definitions.value = definitions.value!!.sortedWith(compareByDescending { it.thumbs_up })
        }
    }
}