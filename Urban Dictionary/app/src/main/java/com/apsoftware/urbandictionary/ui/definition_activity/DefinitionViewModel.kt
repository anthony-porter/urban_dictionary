package com.apsoftware.urbandictionary.ui.definition_activity

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.apsoftware.urbandictionary.data.network.BaseViewModel
import com.apsoftware.urbandictionary.data.network.DefinitionApiRequest
import com.apsoftware.urbandictionary.myapplication.models.Definition
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DefinitionViewModel: BaseViewModel() {

    @Inject
    lateinit var definitionApiRequest: DefinitionApiRequest
    private lateinit var subscription: Disposable

    val searchTerm:MutableLiveData<String> = MutableLiveData()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val defintionList: MutableLiveData<List<Definition>> = MutableLiveData()

    init{
        getDefinitions()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun getData(word: String): Single<List<Definition>> {
        return definitionApiRequest.getDefinitions(word)
    }

    private fun getDefinitions(){
        subscription = definitionApiRequest.getDefinitions(searchTerm.value)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { onRetrievePostListSuccess() },
                { onRetrievePostListError() }
            )
    }


    private fun onRetrievePostListStart(){
       loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(){

    }

    private fun onRetrievePostListError(){

    }
}