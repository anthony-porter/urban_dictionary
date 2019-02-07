package com.apsoftware.myapplication.networking

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.apsoftware.myapplication.models.DefinitionModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UdApiService {
    val udApiRequest: UdApiRequest = UdApiCall.getService()
    internal lateinit var mutableLiveData: MutableLiveData<DefinitionModel>

    fun getDefinitions() {
        UdApiCall.getService().getDefinitions().enqueue(object : Callback<DefinitionModel> {
            override fun onFailure(call: Call<DefinitionModel>?, t: Throwable?) {
                Log.v("retrofit", "call failed")
            }

            override fun onResponse(call: Call<DefinitionModel>?, response: Response<DefinitionModel>?) {
                mutableLiveData.value = response!!.body()
            }
        })
    }
}