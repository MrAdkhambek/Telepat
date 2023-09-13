package com.adkhambek.telepat.app

import androidx.multidex.MultiDexApplication
import com.adkhambek.di.ComponentHolder
import com.adkhambek.telepat.di.component.AppComponent

class App : MultiDexApplication(), ComponentHolder<AppComponent> {

    override val component: AppComponent by lazy { AppComponent(this) }
}