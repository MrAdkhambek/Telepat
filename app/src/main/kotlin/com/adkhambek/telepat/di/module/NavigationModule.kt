package com.adkhambek.telepat.di.module

import com.adkhambek.di.SingleIn
import com.adkhambek.di.scope.UserScope
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides

@Module
@ContributesTo(UserScope::class)
object NavigationModule {

    @Provides
    @SingleIn(UserScope::class)
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    @SingleIn(UserScope::class)
    fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Provides
    @SingleIn(UserScope::class)
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.getNavigatorHolder()
}
