package com.adkhambek.telepat.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentFactory
import com.adkhambek.auth.api.User
import com.adkhambek.di.ComponentHolder
import com.adkhambek.di.android.bindings
import com.adkhambek.di.android.retainComponent
import com.adkhambek.telepat.base.FlowFragment
import com.adkhambek.telepat.di.component.UserComponent
import com.github.terrakok.cicerone.BackTo
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Replace
import javax.inject.Inject

class UserFlowFragment : FlowFragment(), ComponentHolder<UserComponent> {

    companion object {
        const val ARG_USER = "com.adkhambek.telepat.user"
    }

    override val component: UserComponent by retainComponent { _, _ ->
        val user = requireArguments().getParcelable<User>(ARG_USER)
        requireNotNull(user) { "User not found by key : $ARG_USER" }

        val parent = requireActivity().bindings<UserComponent.ParentBindings>()
        UserComponent(parent, user)
    }

    @Inject lateinit var fragmentFactory: FragmentFactory
    @Inject public override lateinit var navigatorHolder: NavigatorHolder

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            navigator.applyCommands(
                commands = arrayOf(
                    BackTo(null),
                    Replace(Screens.Messaging())
                )
            )
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        bindings<UserBinding>().inject(this)
        childFragmentManager.fragmentFactory = fragmentFactory
    }

    interface UserBinding {
        fun inject(fragment: UserFlowFragment)
    }
}
