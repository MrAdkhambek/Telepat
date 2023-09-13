package com.adkhambek.telepat.di.component

import com.adkhambek.auth.api.User
import com.adkhambek.auth.api.UsersProvider
import com.adkhambek.di.scope.UserScope
import com.adkhambek.telepat.di.module.NavigationModule
import com.adkhambek.telepat.di.module.UserBindModule
import com.adkhambek.telepat.ui.UserFlowFragment
import dagger.BindsInstance
import dagger.Component

@UserScope
@Component(
    modules = [
        UserBindModule::class,
        NavigationModule::class,
    ],
    dependencies = [
        UserComponent.ParentBindings::class
    ]
)
interface UserComponent : UserFlowFragment.UserBinding {

    @Component.Builder
    interface Builder {

        fun build(): UserComponent

        @BindsInstance
        fun user(user: User): Builder

        fun deps(user: ParentBindings): Builder
    }

    interface ParentBindings {
        val userProvider: UsersProvider
    }

    companion object {
        operator fun invoke(
            parent: ParentBindings,
            user: User,
        ): UserComponent {
            return DaggerUserComponent
                .builder()
                .deps(parent)
                .user(user)
                .build()
        }
    }
}
