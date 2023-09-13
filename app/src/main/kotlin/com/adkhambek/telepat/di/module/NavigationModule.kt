package com.adkhambek.telepat.di.module

import com.adkhambek.di.scope.UserScope
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
object NavigationModule {

    @Provides
    @UserScope
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    @UserScope
    fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Provides
    @UserScope
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.getNavigatorHolder()
}
