package com.adkhambek.telepat.di.module

import androidx.lifecycle.ViewModel
import com.adkhambek.di.DaggerMap
import com.adkhambek.di.android.vm.AssistedSavedStateViewModelFactory
import dagger.Module
import dagger.multibindings.Multibinds

@Module
interface ViewModelFactoriesModule {

    @Multibinds
    fun viewModels(): DaggerMap<Class<out ViewModel>, ViewModel>

    @Multibinds
    fun assistedViewModels(): DaggerMap<Class<out ViewModel>, AssistedSavedStateViewModelFactory<out ViewModel>>
}
