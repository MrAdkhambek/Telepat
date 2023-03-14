package com.adkhambek.telepat.app

import androidx.multidex.MultiDexApplication
import com.adkhambek.di.ComponentHolder
import com.adkhambek.telepat.di.component.AppComponent
import com.adkhambek.telepat.di.component.DaggerAppComponent

class App : MultiDexApplication(), ComponentHolder<AppComponent> {

    override val component: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}