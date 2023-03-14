package com.adkhambek.telepat.base

import androidx.fragment.app.Fragment
import com.adkhambek.telepat.res.R
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator

abstract class FlowFragment : Fragment(R.layout.fragment_flow), BackButtonListener {

    protected abstract val navigatorHolder: NavigatorHolder
    protected val navigator: Navigator by lazy(LazyThreadSafetyMode.NONE, ::navigatorInitializer)

    private val currentFragment: Fragment?
        get() = childFragmentManager.fragments.firstOrNull(Fragment::isVisible)

    private fun navigatorInitializer(): Navigator {
        return object : AppNavigator(
            activity = requireActivity(),
            fragmentManager = childFragmentManager,
            containerId = R.id.flow_container,
        ){}
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed(): Boolean {
        return when (val fragment = this.currentFragment) {
            is BackButtonListener -> fragment.onBackPressed()
            else -> childFragmentManager.popBackStackImmediate()
        }
    }
}
