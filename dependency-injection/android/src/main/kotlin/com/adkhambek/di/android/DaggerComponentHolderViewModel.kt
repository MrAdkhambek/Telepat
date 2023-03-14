package com.adkhambek.di.android

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import java.util.concurrent.ConcurrentHashMap

/**
 * Use this to create a dagger component scoped to the full lifecycle of a Fragment. Under the hood, this will be backed
 * by a ViewModel and will have an equivalent lifecycle. That means that it will survive configuration changes.
 *
 * The factory will be given a a coroutine scope and the application object that can be used to bind instances to the
 * component. The coroutine scope is the backing ViewModel's viewModelScope.
 */
public typealias Factory<T> = (CoroutineScope, Application) -> T

public inline fun <reified T : Any> Fragment.retainComponent(crossinline factory: Factory<T>): Lazy<T> = lazy {
    ViewModelProvider(this)[DaggerComponentHolderViewModel::class.java].get(factory)
}

public inline fun <reified T : Any> ComponentActivity.retainComponent(crossinline factory: Factory<T>): Lazy<T> = lazy {
    ViewModelProvider(this)[DaggerComponentHolderViewModel::class.java].get(factory)
}

@PublishedApi
internal class DaggerComponentHolderViewModel(
    app: Application,
) : AndroidViewModel(app) {
    val map = ConcurrentHashMap<Class<*>, Any>()

    inline fun <reified T> get(factory: Factory<T>): T {
        return map.getOrPut(T::class.java) { factory(viewModelScope, getApplication()) } as T
    }
}
