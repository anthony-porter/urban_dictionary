package com.apsoftware.myapplication.networking

import android.util.Log
import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.apsoftware.myapplication.models.ApiResponse
import com.apsoftware.myapplication.models.Definition
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_URL: String = "https://mashape-community-urban-dictionary.p.mashape.com/"
const val TAG = "DefinitionRetrofitCl"

class DefinitionRetrofitClient: Callback<ApiResponse> {

    private val definitions: MutableLiveData<List<Definition>> = MutableLiveData()
    var loading: ObservableInt = ObservableInt(View.GONE)

    fun getDefinitions(): MutableLiveData<List<Definition>>{
        return definitions;
    }

    override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
        Log.d(TAG, "SUCCESS" + response.body())
        definitions.value = response.body()!!.list
        loading.set(View.GONE)
    }

    override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
        Log.e(TAG, "Api failure. reason: " + t.message)
        loading.set(View.GONE)
    }

    fun makeCall(searchTerm: String) {
        val definitionApiCall = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build().create(DefinitionApi::class.java).getDefinitions(searchTerm).enqueue(this)
        Log.d(TAG, definitionApiCall.toString())
        loading.set(View.VISIBLE)
    }
}