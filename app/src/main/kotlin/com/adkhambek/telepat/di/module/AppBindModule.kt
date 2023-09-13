package com.adkhambek.telepat.di.module

import androidx.lifecycle.ViewModel
import com.adkhambek.di.android.vm.InjectingSavedStateViewModelFactory
import com.adkhambek.di.android.vm.ViewModelKey
import com.adkhambek.telepat.MainViewModel
import com.adkhambek.telepat.factories.AuthSavedStateViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet

@Module
interface AppBindModule {

    @Binds
    fun bindAuthFragmentFactory(bind: AuthSavedStateViewModelFactory): InjectingSavedStateViewModelFactory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(bind: MainViewModel): ViewModel
}