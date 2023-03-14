package com.adkhambek.telepat.di.component

import com.adkhambek.di.SingleIn
import com.adkhambek.di.scope.ActivityRetainScope
import com.adkhambek.di.scope.AppScope
import com.adkhambek.telepat.MainActivity
import com.squareup.anvil.annotations.ContributesSubcomponent
import com.squareup.anvil.annotations.ContributesTo
import dagger.BindsInstance
import kotlinx.coroutines.CoroutineScope

@SingleIn(ActivityRetainScope::class)
@ContributesSubcomponent(scope = ActivityRetainScope::class, parentScope = AppScope::class)
interface ActivityRetainComponent {

    fun inject(mainActivity: MainActivity)

    @ContributesSubcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance scope: CoroutineScope
        ): ActivityRetainComponent
    }

    @ContributesTo(AppScope::class)
    interface ParentBindings {
        fun activityComponentFactory(): Factory
    }
}
