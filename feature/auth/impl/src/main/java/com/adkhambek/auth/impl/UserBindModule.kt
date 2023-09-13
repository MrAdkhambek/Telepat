package com.adkhambek.auth.impl

import com.adkhambek.auth.api.UsersProvider
import dagger.Binds
import dagger.Module

@Module
interface UserBindModule {

    @Binds
    fun bindUsersProvider(bind: LocaleUserProvider): UsersProvider
}