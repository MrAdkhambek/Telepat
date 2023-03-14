package com.adkhambek.telepat.di.component

import com.adkhambek.auth.api.User
import com.adkhambek.di.SingleIn
import com.adkhambek.di.scope.ActivityRetainScope
import com.adkhambek.di.scope.AppScope
import com.adkhambek.di.scope.UserScope
import com.adkhambek.telepat.MainActivity
import com.squareup.anvil.annotations.ContributesSubcomponent
import com.squareup.anvil.annotations.ContributesTo
import dagger.BindsInstance
import kotlinx.coroutines.CoroutineScope

@SingleIn(UserScope::class)
@ContributesSubcomponent(scope = UserScope::class, parentScope = ActivityRetainScope::class)
interface UserComponent {

    @ContributesSubcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance user: User
        ): UserComponent
    }

    @ContributesTo(ActivityRetainScope::class)
    interface ParentBindings {
        fun userComponentFactory(): Factory
    }
}
