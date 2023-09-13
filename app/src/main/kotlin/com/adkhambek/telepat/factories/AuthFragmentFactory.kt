package com.adkhambek.telepat.factories

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.adkhambek.di.DaggerMap
import com.adkhambek.di.scope.UserScope
import javax.inject.Inject
import javax.inject.Provider

@UserScope
class AuthFragmentFactory @Inject constructor(
    private val fragmentProviders: DaggerMap<Class<out Fragment>, Provider<Fragment>>,
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val clazz = loadFragmentClass(classLoader, className)
        return fragmentProviders[clazz]?.get() ?: super.instantiate(classLoader, className)
    }
}
