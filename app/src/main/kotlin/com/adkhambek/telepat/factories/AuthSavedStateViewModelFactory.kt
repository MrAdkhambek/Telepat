package com.adkhambek.telepat.factories

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.adkhambek.di.DaggerMap
import com.adkhambek.di.SingleIn
import com.adkhambek.di.android.vm.AssistedSavedStateViewModelFactory
import com.adkhambek.di.android.vm.InjectingSavedStateViewModelFactory
import com.adkhambek.di.scope.AppScope
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject
import javax.inject.Provider

@SingleIn(AppScope::class)
@ContributesBinding(scope = AppScope::class)
class AuthSavedStateViewModelFactory @Inject constructor(
    private val assistedFactories: DaggerMap<Class<out ViewModel>, AssistedSavedStateViewModelFactory<out ViewModel>>,
    private val viewModelProviders: DaggerMap<Class<out ViewModel>, Provider<ViewModel>>,
) : InjectingSavedStateViewModelFactory {

    /**
     * Creates instance of ViewModel either annotated with @AssistedInject or @Inject and passes dependencies it needs.
     */
    override fun create(owner: SavedStateRegistryOwner, defaultArgs: Bundle?) =
        object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
            override fun <T : ViewModel> create(
                key: String,
                modelClass: Class<T>,
                handle: SavedStateHandle,
            ): T {
                val viewModel = createAssistedInjectViewModel(modelClass, handle)
                    ?: createInjectViewModel(modelClass)
                    ?: throw IllegalArgumentException("Unknown model class $modelClass")

                try {
                    @Suppress("UNCHECKED_CAST")
                    return viewModel as T
                } catch (e: Exception) {
                    throw RuntimeException(e)
                }
            }
        }

    /**
     * Creates ViewModel based on @AssistedInject constructor and its factory
     */
    private fun <T : ViewModel?> createAssistedInjectViewModel(
        modelClass: Class<T>,
        handle: SavedStateHandle,
    ): ViewModel? {
        val creator = assistedFactories[modelClass]
            ?: assistedFactories.asSequence().firstOrNull {
                modelClass.isAssignableFrom(it.key)
            }?.value
            ?: return null

        return creator.create(handle)
    }

    /**
     * Creates ViewModel based on regular Dagger @Inject constructor
     */
    private fun <T : ViewModel?> createInjectViewModel(modelClass: Class<T>): ViewModel? {
        val creator = viewModelProviders[modelClass]
            ?: viewModelProviders.asSequence().firstOrNull {
                modelClass.isAssignableFrom(it.key)
            }?.value
            ?: return null

        return creator.get()
    }
}
