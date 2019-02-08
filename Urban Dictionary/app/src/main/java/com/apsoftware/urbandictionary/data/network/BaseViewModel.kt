package com.apsoftware.urbandictionary.data.network

import androidx.lifecycle.ViewModel
import com.apsoftware.urbandictionary.ui.definition_activity.DefinitionViewModel

abstract class BaseViewModel: ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(RetrofitModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is DefinitionViewModel -> injector.inject(this)
        }
    }
}