package com.apsoftware.urbandictionary.data.network

import com.apsoftware.urbandictionary.ui.definition_activity.DefinitionViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(RetrofitModule::class)])
interface ViewModelInjector {
    fun inject(postListViewModel: DefinitionViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(retrofitModule: RetrofitModule): Builder
    }
}