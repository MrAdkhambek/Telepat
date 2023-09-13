package com.adkhambek.telepat.di.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.adkhambek.di.android.fragment.ScreenKey
import com.adkhambek.telepat.factories.AuthFragmentFactory
import com.adkhambek.telepat.ui.MessagingFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet

@Module
interface UserBindModule {

    @Binds
    fun bindAuthFragmentFactory(bind: AuthFragmentFactory): FragmentFactory

    @Binds
    @IntoMap
    @ScreenKey(MessagingFragment::class)
    fun bindMessagingFragment(bind: MessagingFragment): Fragment
}